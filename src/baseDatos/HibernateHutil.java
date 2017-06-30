package baseDatos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 *Esta clase genera una única instacnia de una session usando el patron singelton
 * @author agg
 */
public class HibernateHutil {
    
    private static final SessionFactory sessionFactory;
static
{
try
{
sessionFactory = new Configuration().configure().buildSessionFactory();
} catch (HibernateException he)
{
System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
throw new ExceptionInInitializerError(he);
}
}
public static SessionFactory getSessionFactory()
{
return sessionFactory;
}
    
}
