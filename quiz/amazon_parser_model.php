<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Amazon_Parser_Model extends CI_Model {

    public function __construct() {
        parent::__construct();
    }

    public function get_categories($where = null) {
        $query = $this->db->select('id,cat_name');
        $this->db->from('amazon_product_categories');
        $data = $this->db->get()->result_array();

        return $data;
    }

    public function get_products($id, $search_index) {

        try {

            $this->db->select('cat_name');
            $this->db->from('amazon_product_categories');
            $this->db->where('id', $id);
            $data = $this->db->get()->result_array();

            $category = $data[0]['cat_name'];

            $this->load->library('amazon_ecs');
            $result = $this->ecs->category('All')->responseGroup('Large')->search($search_index);
            $result = json_encode($result);

            $products = json_decode($result, TRUE);

            return $products;
        } catch (SoapFault $exception) {
            echo $exception->getMessage();
        }
    }

    public function get_products_below_ten($user_id, $search_index, $page = 1) {

        try {


            $this->load->library('amazon_ecs');
            $Items = $this->_get_product($page);
            $counter = 0;
            $asin = '';
            $this->db->select('point');
            $this->db->from('user_info');
            $this->db->where('user_id', $user_id);
            $data = $this->db->get()->result_array();
            if ($data) {
                $old_point = (int) $data[0]['point'];
                if ($old_point < 20) {
                    $result = array('ASIN' => '', 'Image' => '', 'url' => '', 'Title' => 'You don\'t have sufficient points. Earn More Points.');
                } else {
                    //            $result = array();
                    do {
                        if ($counter == 9) {
                            $counter = 0;
                            $Items = $this->_get_product($page++);
                        }
                        if (isset($Items[$counter]['OfferSummary']['LowestNewPrice']) && $Items[$counter]['OfferSummary']['LowestNewPrice']['Amount'] < 1000 && $Items[$counter]['OfferSummary']['LowestNewPrice']['Amount'] != '') {
                            $tableData = array('user_id' => $user_id,
                                'ASIN' => $Items[$counter]['ASIN'],
                            );
                            $total = $this->db->from('user_amazon_asin')->where($tableData)->count_all_results();

                            if ($total == 0) {
                                $asin = $Items[$counter]['ASIN'];
                                $image = $Items[$counter]['LargeImage']['URL'];
                                $url = $Items[$counter]['DetailPageURL'];
                                $title = $Items[$counter]['ItemAttributes']['Title'];

                                $this->db->insert('user_amazon_asin', $tableData);
                                $result = array('ASIN' => $asin, 'Image' => $image, 'url' => $url, 'Title' => $title);
                            } //end of if total==0
                        }//end of if isset
                        $counter++;
                    } while ($asin == '');
                } // end of if ($old_point < 20)
            }

            return $result;
        } catch (SoapFault $exception) {
            echo $exception->getMessage();
        }
    }

    public function match_amazon_products($asin, $price, $user_id) {
        try {
            $this->load->library('amazon_ecs');
            $product = $this->ecs->responseGroup('Offers')->returnType(1)->lookup($asin);

            $Items = $product['Items']['Item']['OfferSummary']['LowestNewPrice'];
            $price_list = array_values($Items);
//            print_r($price_list);
//            exit();
            $this->db->select('point');
            $this->db->from('user_info');
            $this->db->where('user_id', $user_id);
            $data = $this->db->get()->result_array();
            if ($data) {
                $old_point = (int) $data[0]['point'];

                if ($old_point < 20) {
                    return array('message' => 'No'); // cause point shortage
                } else {
                    $new_point = ($old_point - 20);
                    $data = array(
                        'point' => $new_point,
                    );
                    $where = array('user_id' => $user_id);
                    $this->db->update('user_info', $data, $where);
                    if ($price_list[0] == $price && !empty($user_id)) {

                        $data = array('user_id' => $user_id,
                            'status' => 1,
                            'asin' => $asin,
                            'point' => $price_list[2],
                            'gift_type' => 1);

                       // $query = $this->db->insert('gift_code', $data);
                        return array('message' => 'No');
                    } else {
                        return array('message' => 'No'); // point mismatch
                    }
                }
            } else {
                return array('message' => 'No'); // invalid user
            }
        } catch (SoapFault $exception) {
            echo $exception->getMessage();
        }
    }

    public function _get_product($page) {
        $query = $this->db->query('select * from keywords order by rand() limit 1', FALSE)->result();
        $keyword = $query[0]->keyword;
        $products = $this->ecs->category('All')
                ->page($page)
                ->optionalParameters(
                        array(
                            'Condition' => 'All',
                            'MinimumPrice' => 0100,
                            'MaximumPrice' => 1000
                        )
                )
                ->returnType(1)
                ->responseGroup('ItemAttributes,Offers,Images')
                ->search($keyword);
//        echo '<pre>';
//        var_dump($products);
//        echo '</pre>';
//        exit();

        return $products['Items']['Item'];
    }

    public function add_user_points($user_id, $points, $salt, $sign) {

        define("PRIVATE_KEY", "Ab@#^&bgTyOpq754365465465asdfwSSAdsd");
        $generated_sign = md5(PRIVATE_KEY . $salt);
//        echo $generated_sign;
//        exit();

        if ($sign == $generated_sign && $user_id != '' && $points != '' && is_numeric($user_id) == "true" && is_numeric($points) == "true") {

            $this->db->select('point');
            $this->db->from('user_info');
            $this->db->where('user_id', $user_id);
            $data = $this->db->get()->result_array();

            if ($data) {
                $old_point = $data[0]['point'];
                $new_point = $points + $old_point;


                $data = array(
                    'point' => $new_point,
                );

//                print_r($data);
//                exit();
                $where = array('user_id' => $user_id);
                $q = $this->db->update('user_info', $data, $where);
                return array('message' => 'success');
            } else {
                return array('message' => 'failed');
            }
        } else {
            return array('message' => 'failed');
        }
    }

}
