package com.shopping.dao;

import com.shopping.entity.Card;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class CardDaoImplement implements CardDao{

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<Card> getAllCards() {

        String hql = "from Card";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<Card> pageQuery(int start,int num) {
        String hql = "from Card";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
       query.setFirstResult(start);
       query.setMaxResults(num);
        return query.list();
    }

    @Override
    public boolean deleteCard(int id) {
        System.out.println("要删除的帖子id是  "+id);
        String hql = "delete Card where id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, id);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Card> getCardsByUser(int userId) {
        String hql = "from Card where userId=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,userId);
        return query.list();
    }

    @Override
    public void addCard(Card card) {
        System.out.println("发表content is "+card.getContent());
        System.out.println(card);
        sessionFactory.getCurrentSession().save(card);

    }


    public static void main(String[] args){
        CardDaoImplement cardDaoImplement=new CardDaoImplement();
        Card card=new Card();
        card.setUserId(0);
        card.setContent("llll");
       // CardDaoImplement cardDaoImplement=new CardDaoImplement();
        cardDaoImplement.addCard(card);
       /* List<Card> cardArrayList=new ArrayList<>();
       cardArrayList= cardDaoImplement.getAllCards();
       cardArrayList.forEach(e->{
           System.out.println(e.getContent());
       });*/
    }
}
