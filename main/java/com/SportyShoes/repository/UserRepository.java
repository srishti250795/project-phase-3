package com.SportyShoes.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.SportyShoes.model.User;

@SuppressWarnings("deprecation")
@Component
public class UserRepository
{
	private HibernateTemplate hibernateTemplate;

	public UserRepository() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRepository(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
    public List<User>getAllUsers(){
        
        List<User>userList = hibernateTemplate.loadAll(User.class);
        return userList;
    }
    
    public List<User>searchUser(String email){
        
        Session session=hibernateTemplate.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        
        String hql = "from User where userEmail=:email";
        Query<User>query = session.createQuery(hql,User.class);
        query.setParameter("email", email);
        List<User>user = query.getResultList();
        transaction.commit();
        session.close();
        return user;
    }

}
