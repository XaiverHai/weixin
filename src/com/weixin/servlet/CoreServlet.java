package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.util.SignUtil;

//将servlet写进web文件
public class CoreServlet extends HttpServlet {

	/**
	 * @author 徐海
	 * @since 2017-11-8 确认请求来自微信服务器
	 */
	private static final long serialVersionUID = 1L;

	// 确认请求来自微信服务器,返回正确的echostr
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 微信加密签名
		String signature = req.getParameter("signature");
		// 时间戳
		String timestamp = req.getParameter("timestamp");
		// 随机数
		String nonce = req.getParameter("nonce");
		// 随机字符串
		String echostr = req.getParameter("echostr");

		PrintWriter writer = resp.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		// 对前面三个进行排序然后SHA1-加密，然后和传入值相比较，返回需要值
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			writer.print(echostr);
		}
		writer.close();
		writer = null;

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
