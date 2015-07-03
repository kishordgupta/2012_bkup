package com.tmm.android.chuck.db;

import com.tmm.android.chuck.quiz.Initializeoptions;

public class QueryBuilder {

	
	public static String query="";
	
	static public void QueryBuilderwhereclause()
	{
		
		// table_name like 'boiler' OR table_name like 'environment' 
		String s="";
		if(Initializeoptions.S1.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S1.tablename+"' OR";
		}
		if(Initializeoptions.S2.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S2.tablename+"' OR";
		}
		if(Initializeoptions.S3.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S3.tablename+"' OR";
		}
		if(Initializeoptions.S4.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S4.tablename+"' OR";
		}
		if(Initializeoptions.S5.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S5.tablename+"' OR";
		}
		if(Initializeoptions.S6.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S6.tablename+"' OR";
		}
		if(Initializeoptions.S7.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S7.tablename+"' OR";
		}
		if(Initializeoptions.S8.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S8.tablename+"' OR";
		}
		if(Initializeoptions.S9.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S9.tablename+"' OR";
		}
		if(Initializeoptions.S10.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S10.tablename+"' OR";
		}
		if(Initializeoptions.S11.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S11.tablename+"' OR";
		}
		if(Initializeoptions.S12.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S12.tablename+"' OR";
		}
		if(Initializeoptions.S13.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S13.tablename+"' OR";
		}
		if(Initializeoptions.S14.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S14.tablename+"' OR";
		}
		if(Initializeoptions.S15.shouldadd)
		{
			s=s+" table_name like '"+Initializeoptions.S15.tablename+"' OR";
		}
		
		s=s+" table_name like '"+"obastob"+"'";
		query=s;
	}
}
