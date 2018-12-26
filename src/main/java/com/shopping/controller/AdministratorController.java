package com.shopping.controller;

import com.shopping.bean.AdminContent;
import com.shopping.bean.Goods;
import com.shopping.dao.AdminContentDAO;
import com.shopping.dao.GoodsDAO;
import com.shopping.dao.PostStatusDAO;
import com.shopping.dao.ShopDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AdministratorController {
    /**
     * 列出所有商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminGoodsList",method = RequestMethod.POST)
    public void GoodsList(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<Goods> goodss=new GoodsDAO().seleteAll();
        //System.out.println(goodss.toArray().toString());
        request.setAttribute("goodss",goodss);
        request.getRequestDispatcher("adminGoodsList.jsp").forward(request, response);
    }
    /**
     * 删除商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminDeleteGoods",method = RequestMethod.POST)
    public void AdminDeleteGoods(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String name= request.getParameter("name");
        new GoodsDAO().deleteGoods(name);
        response.getWriter().write("<h1>删除商品完成返回管理员主界面</h1>");
        response.setHeader("refresh","3;/adminMain.jsp");
    }

    /**
     * 删除店铺及其旗下商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminDeleteShop",method = RequestMethod.POST)
    public void AdminDeleteShop(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String owner=request.getParameter("owner");
        new GoodsDAO().deleteOwnerGoods(owner);
        new ShopDAO().deleteShop(owner);
        response.getWriter().write("<h1>删除商家完成返回管理员主界面</h1>");
        response.setHeader("refresh","3;/adminMain.jsp");
    }

    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
    public void AdminLogin(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        if("root".equals(account)&&"admin".equals(password)){
            request.getSession().setAttribute("admin","admin");
            response.getWriter().write("<h1 style='color:green'>管理员登录成功！</h1>");
            response.setHeader("refresh","3;/adminMain.jsp");
        }
        else{
            response.getWriter().write("<h1 style='color:red'>管理员登录失败！</h1>");
        }
    }

    /**
     * 管理员 关闭帖子功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminPostClose",method = RequestMethod.POST)
    public void AdminPostClose(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        new PostStatusDAO().updateStatus(0);
        response.getWriter().println("关闭帖子功能");
        response.getWriter().write("<h1>关闭帖子功能成功！</h1>");
        response.setHeader("refresh","3;/adminMain.jsp");
    }

    /**
     * 管理员开启帖子功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminPostOpen",method = RequestMethod.POST)
    public void AdminPostOpen(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        new PostStatusDAO().updateStatus(1);
        response.getWriter().write("<h1>开启帖子功能成功！</h1>");
        response.setHeader("refresh","3;/adminMain.jsp");
    }

    /**
     * 管理员发出通告
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/adminSayServlet",method = RequestMethod.POST)
    public void AdminSay(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String content=request.getParameter("content");
        AdminContent adminContent=new AdminContent();
        adminContent.setContent(content);
        new AdminContentDAO().addContent(adminContent);
        response.getWriter().write("<h1>管理员发出通告成功！</h1>");
        response.setHeader("refresh","3;/adminMain.jsp");
    }
}
