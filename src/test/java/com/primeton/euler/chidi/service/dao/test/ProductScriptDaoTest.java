/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年8月25日 上午11:14:31
 *******************************************************************************/

package com.primeton.euler.chidi.service.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.primeton.euler.chidi.service.Application;
import com.primeton.euler.chidi.service.dao.ProductScriptDao;
import com.primeton.euler.chidi.service.model.ProductScript;
import com.primeton.euler.chidi.service.util.DbUtils;

/**
 * 
 * TODO 此处填写 class 信息
 *
 * @author tianxh (mailto:tianxh@primeton.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Rollback(false) // true 清空测试数据
@Transactional
public class ProductScriptDaoTest {
	@Autowired
	private ProductScriptDao scriptDao;

	@Test
	public void testQueryById() {
		ProductScript script = scriptDao.queryById("3ee5b782-f648-47a0-bcb5-7a42803bcc57");
		System.out.println(">>>> query by script id: " + script.getScriptContent());
		Assert.assertNotNull(script.getScriptContent());
	}

	@Test
	public void testQueryByProductId() {
		ProductScript script = scriptDao.queryByProductId("1ba4ee03-a09d-4b0e-9332-1d4b8f78071e");
		System.out.println(">>>> query by product id: " + script.getScriptContent());
		Assert.assertNotNull(script.getScriptContent());
	}

	@Test
	public void testQeuryScriptAndExecuteScript() {
		ProductScript script = scriptDao.queryByProductId("1ba4ee03-a09d-4b0e-9332-1d4b8f78071e");
		String url = DbUtils.generateUrl("10.15.15.100:30226", "custom_db");
		DbUtils.executeMySQLScript(url, "root", "c_krit", script.getScriptContent());
	}
}
