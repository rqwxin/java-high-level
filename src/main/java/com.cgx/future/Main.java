package com.cgx.future;

public class Main {

	public static void main(String[] args) {
		FutureClient fc = new FutureClient();
		Data data = fc.request("请求参数");
		 try{  
                
	            //处理其他业务  
	            //这个过程中，真是数据RealData组装完成，重复利用等待时间  
	            Thread.sleep(2000);           
	            doOtherThing()   ;
	        }catch (Exception e){  
	              
	        }  
	          
	        //真实数据  
	        try {
				System.out.println("数据 = "+ data.getResult());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	}
	
	public static void doOtherThing(){
		System.out.println("我在做其它事情");
	}
}
