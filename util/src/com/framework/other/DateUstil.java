package com.framework.other;

public class DateUstil {
	
	public static void main(String[] args) {
		//String info = "<option value="00:30">00:30</option>" ;
		//fn:contains( model.bonNoticeType, 'c') 
		for(int ii=0;ii<24;ii++){
				String hour = ( ii < 10 ? ("0" + ii) : ii ).toString() ;
				String half = (hour + ":00") ;
				String halfThree = (hour + ":30") ;
				System.out.println( "<option value='"+ half + "' <c:if test=\"${ fn:contains( model.vendTime,'" + half+ "') }\">selected='selected'</c:if> >"+ half + "</option>" );
				System.out.println( "<option value='"+ halfThree + "' <c:if test=\"${ fn:contains( model.vendTime,'" + halfThree+ "') }\">selected='selected'</c:if> >"+ halfThree + "</option>" );
		}
	}

}
