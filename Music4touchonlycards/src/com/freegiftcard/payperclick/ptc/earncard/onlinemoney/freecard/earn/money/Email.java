package com.freegiftcard.payperclick.ptc.earncard.onlinemoney.freecard.earn.money;

import com.atomix.kurowiz.xmlparser.DomParser;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Email {

	  public static void email(Context ctx,  String subject, String body)
      {
          try
          {
              // use the builtin chooser for users 'send' app
              Intent sendIntent = new Intent(Intent.ACTION_SEND);
              sendIntent.setType("text/plain");
              String s="[Customer number]\n＊＊＊＊＊＊＊＊＊\nUser ID:" + DomParser.userInfo.getUserId()+"\n※ Because it will be used on the management side, we ask you not to delete that.If it is removed, we can not answer your inquiry,Please be careful.";

//※ Because it will be used on the management side, we ask you not to delete that.If it is removed, we can not answer your inquiry,Please be careful.";

              sendIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String [] {"despicableapps@gmail.com"});
              sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "User ID" + DomParser.userInfo.getUserId()+ "Android Giftcard");
              sendIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);

              ctx.startActivity (Intent.createChooser(sendIntent, "Send Email..."));
          }
          catch (Exception e) 
          {
              Toast.makeText (ctx, "No email account is found on this device",Toast.LENGTH_SHORT).show();
          }
      }
}
