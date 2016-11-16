package com.constructor;

import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;  
import org.springframework.core.io.*;  
  
public class Test {  
    public static void main(String[] args) {  
          
        Resource r=new ClassPathResource("constructorbeans.xml");  
        BeanFactory factory=new XmlBeanFactory(r);  
          
        Employee s=(Employee)factory.getBean("e");  
        Employee s1=(Employee)factory.getBean("f");
        s.show();
        s1.show();
          
    }  
}  

