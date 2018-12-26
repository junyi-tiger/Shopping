package com.shopping.controller;

import com.shopping.bean.Goods;
import com.shopping.bean.Image;
import com.shopping.bean.Shop;
import com.shopping.dao.GoodsDAO;
import com.shopping.dao.ImageDAO;
import com.shopping.dao.ShopDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class ShopController {
    /**
     * 列出商家的所有商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/shopGoodsList",method = RequestMethod.POST)
    public void GoodsList(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<Goods> goodss=new GoodsDAO().seleteShopGoods(request.getParameter("account"));
        //System.out.println(goodss.toArray().toString());
        request.setAttribute("goodss",goodss);
        request.getRequestDispatcher("adminListGoods.jsp").forward(request, response);
    }

    /**
     * 商家添加商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/shopMain",method = RequestMethod.POST)
    public void GoodsAdd(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "photo")MultipartFile file)
            throws Exception{
        String name=request.getParameter("name");
        String photo="photo";
        if (!file.isEmpty()){
            Image image = new Image();
            image.setGood_name(name);
            image.setPhoto(file.getBytes());
            image.setDescription("这是"+name+"的图片");
            new ImageDAO().addImage(image);
            System.out.println("保存图片成功！");
        }
        double price=Double.parseDouble(request.getParameter("price"));
        String owner=request.getParameter("owner");
        Goods goods=new Goods();
        goods.setName(name);
        goods.setOwner(owner);
        goods.setPhoto(photo);
        goods.setPrice(price);
        new GoodsDAO().addGoods(goods);
        response.getWriter().write("<h1>添加商品成功！</h1>");
    }

    /**
     * 获取商品的图片
     * @param good_name 商品名称
     */
    @RequestMapping(value = "/showImage",method = RequestMethod.GET)
    public void showImage(@RequestParam(name="name")String good_name,HttpServletRequest request,HttpServletResponse response)
        throws Exception{
        InputStream inputStream = new ImageDAO().getImage(request.getParameter("name"));
        byte []b =new byte[inputStream.available()];
        inputStream.read(b);
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(b);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 商家删除商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/shopDeleteGoods",method = RequestMethod.POST)
    public void ShopDeleteGoods(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String name=request.getParameter("name");
        new GoodsDAO().deleteGoods(name);
        response.getWriter().write("<h1>删除商品完成返回商家主界面</h1>");
        response.setHeader("refresh","3;/shopMain.jsp");
    }

    /**
     * 商家列出商品
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/shopListGoods",method = RequestMethod.POST)
    public void ShopListGoods(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String account=request.getParameter("account");
        //System.out.println(account);
        List<Goods> goodss=new GoodsDAO().seleteShopGoods(account);
        request.setAttribute("products", goodss);
        request.getRequestDispatcher("shopListGoods.jsp").forward(request, response);
    }

    /**
     * 商家登录
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/shopLogin",method = RequestMethod.POST)
    public void ShopLogin(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        Shop shop=new Shop();
        shop.setAccount(account);
        shop.setPassword(password);
        List<Shop> shops=new ShopDAO().seleteAll();
        boolean f=false;
        for(int i=0;i<shops.size();++i)
            if(account.equals(shops.get(i).getAccount())&&password.equals(shops.get(i).getPassword()))
                f=true;
        if(f){
            response.getWriter().write("<h1>账号存在登录成功！</h1>");
            request.getSession().setAttribute("shop",shop);
            response.setHeader("refresh","3;/shopMain.jsp");
        }
        else {
            response.getWriter().write("<h1>账号或密码错误！登录失败！</h1>");
            response.setHeader("refresh","3;/shopLogin.jsp");
        }
    }

    @RequestMapping(value = "/shopRegister",method = RequestMethod.POST)
    public void ShopRegister(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        Shop shop=new Shop();
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        shop.setAccount(account);
        shop.setPassword(password);
        ShopDAO dao = new ShopDAO();
        if (dao.shopExists(shop.getAccount())){
            response.getWriter().println("<h1>账号已存在，注册失败</h1>");
            response.setHeader("refresh","3;/shopLogin.jsp");
        }else{
            dao.addShop(shop);
            response.getWriter().println("<h1>注册成功</h1>");
            response.setHeader("refresh","3;/shopLogin.jsp");
        }

    }

}
