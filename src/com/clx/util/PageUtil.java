package com.clx.util;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class PageUtil {

	private HibernateTemplate hibernateTemplate;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
	public int getPageMaxCount(String className,int pageSize) {
		String hql = "select count(*) from "+className;
		int maxcount = hibernateTemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				Query query = arg0.createQuery(hql);
				query.setMaxResults(1);
				long i = (long)query.list().iterator().next();
				return (int)i;
			}
		});
		return (maxcount % pageSize==0 )? (maxcount/pageSize) : maxcount/pageSize+1;
	}
	
	public List<?> getPageList(String className,int pageSize,int pageAppoint){
		int pageMaxCount=getPageMaxCount(className, pageSize);
		pageAppoint=pageAppoint<1?1:pageAppoint;
		pageAppoint=pageAppoint>pageMaxCount?pageMaxCount:pageAppoint;
		String hql=" from "+className;
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult((pageAppoint-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	public String getBottomBar(int pageMaxCount,int pageAppoint,String url) {
		pageAppoint=pageAppoint<1?1:pageAppoint;
		StringBuffer buffer = new StringBuffer();
		for (int i = 1; i <=pageMaxCount; i++) {
			if (i==pageAppoint) {
				buffer.append("【"+pageAppoint+"】");
			}
			else buffer.append("<a href=\""+url+"pageAppoint="+i+"\">"+i+"</a>&nbsp;&nbsp;");
		}
		return buffer.toString();
	}
	
}
