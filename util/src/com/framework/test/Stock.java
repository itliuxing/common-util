package com.framework.test;

public class Stock {
	
	public static void main(String[] args) {
		mothods( 22 , 16.8 ) ;
	}
	
	public static void mothods(double sums , double threthold){
		int f = 0;
		boolean mark = true ;
		StringBuffer message = new StringBuffer( "stock价从'" ) ;
		message.append( sums ).append("'") ;
		while( mark ){
			sums = sums - sums*0.1 ;
			f += 1 ;
			if( sums < threthold ){
				mark = false ;
			}
		}
		message.append( "经过--" ).append( f ).append("--轮跌停，当前stock价到达：").append( sums ) ;
		System.out.println( message.toString() );
	}
	
}
