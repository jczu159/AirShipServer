package com.airship.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.airship.server.bo.HibernateSessionFactory;
import com.airship.server.pojo.BetOrderPojo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * 
 * @author admin //接收數據取得下單數據
 */
@Controller
public class DataSwapping {

	private static final Logger logger = LogManager.getLogger(DataSwapping.class);

	@RequestMapping(value = "/getbetdata", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getbetdata(@RequestParam(value = "account", required = true) String account,
			@RequestParam(value = "peroid", required = true) String peroid) {

		String respStr = "";
		Session hibernatesession = HibernateSessionFactory.getSession();
		Transaction transaction = hibernatesession.beginTransaction();
		List<BetOrderPojo> betoder = new ArrayList<BetOrderPojo>();
		try {

			System.out.println("in controller" + account);
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			// 取得連線者的ip
			String remoteAddr = req.getRemoteAddr();
			logger.debug("-------------------------------");
			logger.debug("此次客戶連線ip :" + remoteAddr + " 此次客戶連線帳號 :  " + account);
			logger.debug("-------------------------------");

			String sql = "SELECT * FROM bet_account WHERE ACCOUNT = '" + account + "' AND ENABLE_CLOSE = 'Y' ";
			SQLQuery query = hibernatesession.createSQLQuery(sql);
			List<Object[]> list = query.list();
			if (list.isEmpty()) {
				logger.error("查詢不到該帳號 : " + account);
				throw new Exception("您帳號目前尚無法使用，請洽詢管理員");
			}
			logger.debug("開始查詢第:" + peroid + "的下單資訊");
			String queryOrderSql = "SELECT * FROM bet_both_number WHERE PEROID = '" + peroid + "'";
			SQLQuery qulist = hibernatesession.createSQLQuery(queryOrderSql).addEntity(BetOrderPojo.class);
			betoder = qulist.list();

			respStr = new Gson().toJson(betoder);
			logger.debug("取得到的JSON結果 : " + respStr);

		} catch (Exception e) {
			respStr = e.toString();
			logger.error("程式發生錯誤:" + e);
		} finally {
			transaction.commit();
			hibernatesession.close();
		}
		return respStr;
	}

	public static String getRunVersion(@RequestParam(value = "version", required = true) String account) {

		return null;
	}
}
