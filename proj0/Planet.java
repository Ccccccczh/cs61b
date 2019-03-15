public class Planet{

    final double G = 6.67E-11;
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;


    public Planet(double xP, double yP, double xV, double yV, double m, String img){
      this.xxPos = xP;
      this.yyPos = yP;
      this.xxVel = xV;
      this.yyVel = yV;
      this.mass = m;
      this.imgFileName = img;
    }

    public Planet(Planet p){
      this.xxPos = p.xxPos;
      this.yyPos = p.yyPos;
      this.xxVel = p.xxVel;
      this.yyVel = p.yyVel;
      this.mass = p.mass;
      this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
      double r = Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos));
      return r;
    }

    public double calcForceExertedBy(Planet p){
      double F;
      double r = this.calcDistance(p);
      F = (G * mass * p.mass)/(r*r);
      return F;
    }

    public double calcForceExertedByX(Planet p){
      double dx = p.xxPos - this.xxPos;
      double Fx = (this.calcForceExertedBy(p) * dx) / this.calcDistance(p);
      return Fx;
    }

    public double calcForceExertedByY(Planet p){
      double dy = p.yyPos - this.yyPos;
      double Fy = (this.calcForceExertedBy(p) * dy) / this.calcDistance(p);
      return Fy;
    }

    public double calcNetForceExertedByX(Planet[] p){
      double[] Fx = new double[p.length];
      double Fxnet = 0;
      for(int i = 0; i < p.length; i += 1){
        if (!p[i].equals(this)){
          Fx[i] = this.calcForceExertedByX(p[i]);
          Fxnet = Fxnet + Fx[i];
        }
      }
      return Fxnet;
    }

    public double calcNetForceExertedByY(Planet[] p){
      double[] Fy = new double[p.length];
      double Fynet = 0;
      for(int i = 0; i < p.length; i += 1){
        if (!p[i].equals(this)){
          Fy[i] = this.calcForceExertedByY(p[i]);
          Fynet = Fynet + Fy[i];
        }
      }
      return Fynet;
    }

    public void update(double dt, double fx, double fy){
      double ax;
      double ay;

      ax = fx / mass;
      ay = fy / mass;

      xxVel = xxVel + dt * ax;
      yyVel = yyVel + dt * ay;

      xxPos = xxPos + dt * xxVel;
      yyPos = yyPos + dt * yyVel;

    }
}
