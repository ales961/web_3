import jpa.DotsEntity;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bean implements Serializable {
    private double x1;
    private double y1;
    private double r1;
    private double x;
    private double y;
    private double r;
    private String res;
    public ArrayList<DotsEntity> points = new ArrayList<DotsEntity>();

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getR() {
        return r;
    }
    public void setR(double r) {
        this.r = r;
    }
    public double getX1() {
        return x1;
    }
    public void setX1(double x1) {
        this.x1 = x1;
    }
    public double getY1() {
        return y1;
    }
    public void setY1(double y1) {
        this.y1 = y1;
    }
    public double getR1() {
        return r1;
    }
    public void setR1(double r1) {
        this.r1 = r1;
    }
    public String getRes() {
        return res;
    }
    public void setRes(String res) {
        this.res = res;
    }
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    public void add() throws IOException {
        try {
            DotsEntity dots = new DotsEntity();
            dots.setX((float) x);
            dots.setX((float) y);
            dots.setX((float) r);
            dots.setHit(check(x,y,r));
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(dots);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch(Exception e){
            System.out.println(e);
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public ArrayList<DotsEntity> getPoints() throws SQLException, ClassNotFoundException {
        points.clear();
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("SELECT a from DotsEntity as a");
        List<DotsEntity> dotsEntities = query.getResultList();
        for (DotsEntity dotsEntity : dotsEntities) {
            points.add(dotsEntity);
        }
        return points;
    }

    private String check(double x, double y, double r) {
        if(x < 0 && y > 0 && y <= x + r/2) return "+";
        else if(x > 0 && y < 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) return "+";
        else if (x < 0 && y < 0 && y >= -r && x >= -r) return "+";
        else return "-";
    }

    String str;
    public String getStr() throws SQLException, ClassNotFoundException {
        str = "";
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery("SELECT a from DotsEntity as a");
        List<DotsEntity> dotsEntities = query.getResultList();
        for (DotsEntity dotsEntity : dotsEntities) {
            str += String.valueOf(dotsEntity.getX())+"#"+
                    String.valueOf(dotsEntity.getY())+"#"+
                    String.valueOf(dotsEntity.getHit())+"#";
        }
        return str;
    }

    public void add_p() throws IOException {
        try {
            DotsEntity dots = new DotsEntity();
            dots.setX((float) x1);
            dots.setX((float) y1);
            dots.setX((float) r);
            dots.setHit(check(x1,y1,r1));
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(dots);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch(Exception e){
            System.out.println(e);
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}

