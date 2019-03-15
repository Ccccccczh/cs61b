import java.util.Scanner;

public class NBody{

  public static double readRadius(String file){
    In in = new In(file);

    int numberOfPlanet = in.readInt();
    double radius = in.readDouble();

    return radius;
  }

  public static Planet[] readPlanets(String file){
    In in = new In(file);

    int numberOfPlanet = in.readInt();
    Planet[] p = new Planet[numberOfPlanet];
    double radius = in.readDouble();

    for(int i = 0; i < numberOfPlanet; i += 1){
        double pix = in.readDouble();
        double piy = in.readDouble();
        double pivx = in.readDouble();
        double pivy = in.readDouble();
        double pimass = in.readDouble();
        String piImageFile = in.readString();

        Planet pi = new Planet(pix, piy, pivx, pivy, pimass, piImageFile);
        p[i] = pi;
      }
    return p;
    }

    public static void main(String[] args) {
        double T;
        double dt;
        String filename;

        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        String imageToDraw = "images/starfield.jpg";
        // StdDraw.setPenRadius(r);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw, 2, 2);
        // StdDraw.show();
        // StdDraw.pause(200000);

        StdDraw.enableDoubleBuffering();
        for (double t = 0; t< T; t += dt){
            double[] xForce = new double[5];
            double[] yForce = new double[5];
            for(int i = 0; i<planets.length; i += 1){
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < 5; i += 1){
            planets[i].update(dt, xForce[i], yForce[i]);
            }

            StdDraw.picture(0, 0, imageToDraw,2, 2);
            for(Planet planet : planets){
                planet.draw();
            }
            StdDraw.show(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}



    }
}
