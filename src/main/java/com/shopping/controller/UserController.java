package com.shopping.controller;

import com.shopping.bean.*;
import com.shopping.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping(value = "/userAddCart",method = RequestMethod.POST)
    public void UserAddCart(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String goods_name=request.getParameter("name");
        int goods_num=Integer.parseInt(request.getParameter("num"));
        String user_account= request.getParameter("user_account");
        String shop_account=request.getParameter("shop_account");
        double price=Double.parseDouble(request.getParameter("price"));
        double sum=price*goods_num;
        Order order=new Order();
        order.setGoods_price(price);
        order.setGoods_name(goods_name);
        order.setGoods_num(goods_num);
        order.setShop_account(shop_account);
        order.setStatus(0);
        order.setUser_account(user_account);
        order.setSum(sum);
        List<Order> orders=(List<Order>)request.getSession().getAttribute("orders");
        if(null==orders){
            orders=new ArrayList<Order>();
            request.getSession().setAttribute("orders",orders);
        }
        orders.add(order);
        //response.sendRedirect("/userSeeCart");
        response.getWriter().write("<h1>购物车添加成功！</h1>");
        response.setHeader("refresh","3;/userCart.jsp");
    }

    @RequestMapping(value = "/userAddOrder",method = RequestMethod.POST)
    public void UserAddOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String goods_name=request.getParameter("goods_name");
        //String goods_name=new String(temp_goods_name.getBytes("ISO-8859-1"),"UTF-8");
        //System.out.println(temp_goods_name);
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        List<Order> ordersDelete =new ArrayList<>();
        if(null!=orders){
            for (Order io : orders) {
                if(io.getGoods_name().equals(goods_name)){
                    ordersDelete.add(io);
                    //System.out.println(0);
                }
            }
        }
        orders.removeAll(ordersDelete);
        new OrderDAO().addOrder(ordersDelete.get(0));
        response.getWriter().write("<h1>生成订单成功</h1>");
        response.setHeader("refresh","2;/userMain.jsp");
        //System.out.println(ordersDelete.get(0).getGoods_name());
    }

    @RequestMapping(value = "/userAddPost",method = RequestMethod.POST)
    public void UserAddPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String goods_name=request.getParameter("goods_name");
        //String goods_name=new String(temp_goods_name.getBytes("ISO-8859-1"),"UTF-8");
        //System.out.println(temp_goods_name);
        String user_account=request.getParameter("user_account");
        String content=request.getParameter("content");
        //System.out.println(user_account);
        int user_member=new UserDAO().selectMember(user_account);
        if(user_member==0){
            response.getWriter().write("<h1>你不是会员不能发表帖子！</h1>");
            response.setHeader("refresh","3;/userMain.jsp");
        }
        else{
            Post post=new Post();
            post.setContent(content);
            post.setUser_account(user_account);
            new PostDAO().addPost(post);
            response.getWriter().write("<h1>发表帖子成功</h1>");
            response.setHeader("refresh","3;userMain.jsp");
        }
    }

    @RequestMapping(value = "/userDeleteCart",method = RequestMethod.GET)
    public void UserDeleteCart(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String temp_goods_name=request.getParameter("goods_name");
        String goods_name=new String(temp_goods_name.getBytes("ISO-8859-1"),"UTF-8");
        //System.out.println(goods_name);
        List<Order> orders = (List<Order>) request.getSession().getAttribute("orders");
        List<Order> ordersDelete =new ArrayList<>();
        if(null!=orders){
            for (Order io : orders) {
                if(io.getGoods_name().equals(goods_name)){
                    ordersDelete.add(io);
                    System.out.println(0);
                }
            }
        }
        orders.removeAll(ordersDelete);
        response.sendRedirect("/userCart.jsp");
    }

    @RequestMapping(value = "/userListPost",method = RequestMethod.POST)
    public void UserListPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<Post> posts=new PostDAO().seleteAll();
        request.setAttribute("products",posts);
        request.getRequestDispatcher("userMain.jsp").forward(request, response);
    }

    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public void UserLogin(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        List<User> users=new UserDAO().seleteAll();
        boolean f=false;
        for(int i=0;i<users.size();++i)
            if(account.equals(users.get(i).getAccount())&&password.equals(users.get(i).getPassword()))
                f=true;
        if(f){
            request.getSession().setAttribute("user",user);
            response.getWriter().write("<h1>顾客账号存在登录成功！</h1>");
            response.setHeader("refresh","3;/userMain.jsp");
        }
        else response.getWriter().write("<h1>顾客账号不存在或密码错误！登录失败！</h1>");
    }

    /**
     * 用户注册会员
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/userMember",method = RequestMethod.POST)
    public void UserMember(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String account=request.getParameter("account");
        UserDAO dao = new UserDAO();
        if (dao.isMember(account)){
            response.getWriter().write("<h1>"+account+"您已经是会员了，无需注册！</h1>");
            //request.getSession().setAttribute("shop",shop);
            response.setHeader("refresh","3;/userMain.jsp");
        } else{
            dao.updateMember(account);
            response.getWriter().write("<h1>"+account+"注册会员成功！</h1>");
            //request.getSession().setAttribute("shop",shop);
            response.setHeader("refresh","3;/userMain.jsp");
        }

    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public void UserRegister(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        User user=new User();
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        user.setAccount(account);
        user.setPassword(password);
        user.setMember(0);
        UserDAO dao = new UserDAO();
        //System.out.println(shop.getAccount()+","+shop.getPassword());
        if (dao.userExist(user.getAccount())){
            response.getWriter().println("<h1>用户账号已存在，注册失败！</h1>");
            response.setHeader("refresh","3;/userLogin.jsp");
        } else {
            dao.addUser(user);
            response.getWriter().println("<h1>注册成功</h1>");
            response.setHeader("refresh","3;/userLogin.jsp");
        }

    }

    /**
     * 用户查找商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/userSearchGoods",method = RequestMethod.POST)
    public void UserSearchGoods(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String name=request.getParameter("name");
        List<Goods> goodss=new GoodsDAO().seleteGoods(name);
        request.getSession().setAttribute("goodss",goodss);
        //response.setHeader("refresh","3;/userMain.jsp");
        request.getRequestDispatcher("userMain.jsp").forward(request, response);
    }

    /**
     * 用户查看商品的评价
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/userSeeAccess",method = RequestMethod.POST)
    public void UserSeeAccess(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String name=request.getParameter("name");
        List<Access> accesses=new AccessDAO().seleteAccessByGoods(name);
        //System.out.println(name);
        /*for(int i=0;i<accesses.size();++i)
            System.out.println(accesses.get(i).getContent()+","+accesses.get(i).getUser_account());
        */
        request.getSession().setAttribute("accesses",accesses);
        request.getRequestDispatcher("userSeeAccess.jsp").forward(request, response);
    }

    @RequestMapping(value = "/userDeleteAccess",method = RequestMethod.POST)
    public void UserDeleteAccess(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String name=request.getParameter("name");

        List<Access> accesses=new AccessDAO().seleteAccessByGoods(name);
        //System.out.println(name);
        /*for(int i=0;i<accesses.size();++i)
            System.out.println(accesses.get(i).getContent()+","+accesses.get(i).getUser_account());
        */
        request.getSession().setAttribute("accesses",accesses);
        request.getRequestDispatcher("userSeeAccess.jsp").forward(request, response);
    }

    @RequestMapping(value = "/userSeeOrder",method = RequestMethod.POST)
    public void UserSeeOrder(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String user_account=request.getParameter("user_account");
        List<Order> listorders=new OrderDAO().seleteOrderByUser(user_account);
        request.setAttribute("listorders", listorders);
        request.getRequestDispatcher("/userSeeOrder.jsp").forward(request, response);
    }

    @RequestMapping(value = "/userWriteAccess",method = RequestMethod.POST)
    public void UserWriteAccess(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String user_account=request.getParameter("user_account");
        String goods_name=request.getParameter("goods_name");
        String content=request.getParameter("content");
        Access access=new Access();
        access.setContent(content);
        access.setUser_account(user_account);
        access.setGoods_name(goods_name);
        new AccessDAO().addAccess(access);
        response.getWriter().write("<h1>发布对商品"+goods_name+"评价成功</h1>");
        //response.setHeader("refresh:3",);
        response.setHeader("refresh","2;/userMain.jsp");
    }
}