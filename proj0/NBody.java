public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		double numOfPlanet = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int numOfPlanet = in.readInt();
		double radius = in.readDouble();

		Planet[] ps = new Planet[numOfPlanet];

		for (int i = 0; i < numOfPlanet; ++i) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			ps[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}

		return ps;
	}

	// $ java NBody 157788000.0 25000.0 data/planets.txt
	// $ java NBody 20000000 20000 ./data/suninterference.txt
	public static void main(String[] args) {
		// Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] ps = readPlanets(filename);
		double radius = readRadius(filename);

		// Set the scale
		StdDraw.setScale(-radius, radius);

		// Creating an Animation
		StdDraw.enableDoubleBuffering();

		double[] xForces = new double[ps.length];
		double[] yForces = new double[ps.length];

		for (int t = 0; t < T; t += dt) {
			for (int i = 0; i < ps.length; ++i) {
				xForces[i] = ps[i].calcNetForceExertedByX(ps);
				yForces[i] = ps[i].calcNetForceExertedByY(ps);
				ps[i].update(dt, xForces[i], yForces[i]);
			}
			for (int i = 0; i < ps.length; ++i) {
				ps[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : ps) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		// Printing the Universe
		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < ps.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
			ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
			ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
		}
	}
}