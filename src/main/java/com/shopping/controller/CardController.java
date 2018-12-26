package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Card;
import com.shopping.service.CardService;
import com.shopping.utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CardController {
    @Resource
    private   CardService cardService;

    @RequestMapping(value = "/send_card")
    public String sendCard() {
        return "send_card";
    }

    @RequestMapping(value = "/all_cards")
    public String allCards() {
        return "all_cards";
    }

    @RequestMapping(value = "/getAllCards",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllCards(){
        List<Card> cardsList = new ArrayList<>();
        cardsList = cardService.getAllCards();
        for(int i=0;i<cardsList.size();i++){
            System.out.println(cardsList.get(i).getId());
        }
        String allCards = JSONArray.toJSONString(cardsList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allCards",allCards);
        return resultMap;
    }
    @RequestMapping(value = "/Post", method = RequestMethod.POST)
    @ResponseBody
    public void Post( int isopen,HttpSession httpSession) {
      System.out.println(isopen);
        httpSession.setAttribute("isopen",isopen);
    }


@RequestMapping(value = "/deleteCard",method = RequestMethod.POST)
@ResponseBody
public Response deleteCard(int id) {
      System.out.println("被删的id是："+id);
    return cardService.deleteCard(id);
}

    @RequestMapping(value = "/pageQuery",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> pageQuery(int star,int num){
        List<Card> cardsList = new ArrayList<>();
        cardsList = cardService.pageQuery(star,num);
        String threeCards = JSONArray.toJSONString(cardsList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("threeCards",threeCards);
        return resultMap;
    }

    @RequestMapping(value = "/getCardsByUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCardByUser(int userId) {
        List<Card> cardList = new ArrayList<>();
        cardList = cardService.getCardsByUser(userId);
        String userCards = JSONArray.toJSONString(cardList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("userCards",userCards);
        return resultMap;
    }

    @RequestMapping(value = "/addCard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCard(String content,int userId) {
        System.out.println(userId+"添加了："+content);
        String result ="fail";
        Card card=new Card();
        card.setContent(content);
        card.setUserId(userId);
        System.out.println("发表的内容为"+card.getContent());
        cardService.addCard(card);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result","success");
        return resultMap;
    }
   /*public static void main(String[] args){
       Card card=new Card();
       card.setContent("hello");
       card.setUserId(1);
       System.out.println(card.getContent());
       cardService.addCard(card);
   }*/
}
