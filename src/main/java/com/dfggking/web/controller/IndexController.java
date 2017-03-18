package com.dfggking.web.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dfggking.common.base.controller.BaseController;

@Controller
@RequestMapping(value = "/front")
public class IndexController extends BaseController {
	
	/**
	 * 为什么是static静态的？
	 * 使用static修饰的属性归这个类使用的，无论这个类实例化多少个，大家用的都是同一个static属性
	 * log4j记录的是当前类的日志，不是每个实例的日志
	 * 所以只要有一个记录就行了
	 *  因为同一个logger name对应唯一的Logger实例，而Logger.getLogger方法在从logger容器中取logger的过程中，有一个步骤被加了同步锁， 这就造成了如果在多线程的竞争环境中频繁地调用getLogger操作，有可能会造成堵塞。而且这种操作是没有价值的，所以使用static来保证需要需 用Logger的类所有的对象共享一个Logger的引用，并且只获取一次就可以了。
	 * 为什么是final？
	 * 防止多logger同时使用时，Logger引用不小心被赋值，这是一个良好的编程习惯。
	 */
	private final static Logger log = LogManager.getLogger(IndexController.class);
	
	/**
	 * 前端页面跳转
	 * <p></p>
	 * <pre></pre>
	 * @param request
	 * @param response
	 * @author jinyf   
	 * @date 2017年3月8日 下午3:09:22 
	 * @since 1.0
	 */
	@RequestMapping(value = "main")
	public String frontMain(HttpServletRequest request, HttpServletResponse response){
		
		log.debug("front/index");
		return "/front/main";
	}
	
	/**
	 * 
	 * <p>微信公众号接口握手验证</p>
	 * <pre></pre>
	 * @param signature 加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param echostr 随机字符串
	 * @author jinyf   
	 * @throws IOException 
	 * @date 2017年2月20日 上午9:31:13 
	 * @since 1.0
	 */
	@RequestMapping(value = "wechat/hello")
	public void hello(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException {
		log.info("signature: " + signature);
		log.info("timestamp: " + timestamp);
		log.info("nonce: " + nonce);
		log.info("echostr: " + echostr);
		log.info("return new");
		OutputStream os = response.getOutputStream();
		os.write(echostr.getBytes("UTF-8"));
		os.flush();
		os.close();
	}
	
}