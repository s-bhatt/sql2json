/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shivang.bhatt
 */
@WebServlet(urlPatterns = {"/Refresh"})
public class Refresh extends HttpServlet {
    int i=0;
    {
        i=i+1;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException
    {
        response.setIntHeader("Refresh", 1);
        response.setContentType("text/html");
        int j=0;
        i=i+2;
        j=j+1;
        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        if(calendar.get(Calendar.AM_PM)==0)
            am_pm="AM";
        else
            am_pm="PM";

        String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
        PrintWriter out = response.getWriter();
        String title ="Auto Refresh Header Setting";
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 "+
        "transitional//en\">\n";
        out.println(docType +
        "<html>\n"+
        "<head><title>"+ title +"</title></head>\n"+
        "<body bgcolor=\"#f0f0f0\">\n"+
        "<h1 align=\"center\">"+ title +"</h1>\n"+
        "<p>Current Time is: "+ CT +"</p>\n"+
        "<p>i and j are: " +i+ " " +j+ "</p>\n");
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
        doGet(request, response);
}
    
}
