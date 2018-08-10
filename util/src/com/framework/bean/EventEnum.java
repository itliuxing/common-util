package com.framework.bean;

/***
 * *
 * 类名称：		Constants.java 
 * 类描述：   		事件信息枚举类,枚举信息取值的一种新方式
 * 创建人：		
 * 创建时间：		2016-8-4下午2:05:16 
 * 修改人：		liuxing
 * 修改时间：		2016-8-4下午2:05:16 
 * 修改备注：   		
 * @version
 */
public enum EventEnum {
	
	event_status_1( 1 , "登录" ) ,	
	event_status_2( 2 , "注册" ) ,	
	event_status_3( 3 , "进入洞口" ) ,	
	event_status_4( 4 , "离开洞口" ) ,	
	event_status_5( 5 , "在二衬区" ) ;
	
	public String value ;
	public int id ;

	EventEnum() {}
	
	EventEnum( int id , String value ){
		this.id = id ;
		this.value = value ;
	}
	
	/***
	 * 新版获取枚举类的数据值
	 * 这个版本才是效率最高
	 * @param status
	 * @return
	 */
	public static String returnStausMessage1(int status){
		for ( EventEnum code : EventEnum.values() ) {  
            if ( code.id == status ) {  
                return code.value;  
            }  
        }  
		return null ;
	}
	
	/***
	 * 老版获取枚举类的数据值
	 * Because is too seconds, move it to { @link returnStausMessage1 } }
	 * @param status
	 * @return
	 */
	@Deprecated
	public static String returnStausMessage(int status){
		String value = null ;
		switch ( status ) {
		case 1:
			value = "登录" ;
			break;
		case 2:
			value = "注册" ;
			break;
		case 3:
			value = "进入洞口" ;
			break;
		case 4:
			value = "离开洞口" ;
			break;
		case 5:
			value = "在二衬区" ;
			break;
		default:
			break;
		}
		return value ;
	}
	
}
