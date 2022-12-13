package com.SportyShoes.repository;

import java.util.List;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.SportyShoes.model.PurchaseReport;

@Component
public class PurchaseReportRepository 
{
	private HibernateTemplate hibernateTemplate;

	public PurchaseReportRepository() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseReportRepository(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("deprecation")
    public List<PurchaseReport>getReport(){
        
        @SuppressWarnings("unchecked")
        List<PurchaseReport>report = (List<PurchaseReport>) hibernateTemplate.find("from PurchaseReport order by reportProductName,reportDate");
        
        return report;
    }

}
