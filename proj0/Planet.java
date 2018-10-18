public class Planet {
	
	// public static final double G = 6.67 * Math.pow(10, -11);
	public static final double G = 6.67e-11;

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistanceX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		
		return dx;
	}

	public double calcDistanceY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		
		return dy;
	}

	public double calcDistance(Planet p) {
		double dx = this.calcDistanceX(p);
		double dy = this.calcDistanceY(p);
		double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

		return d;
	}

	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		double f = (G * this.mass * p.mass) / Math.pow(r, 2);

		return f;
	}

	public double calcForceExertedByX(Planet p) {
		double dx = this.calcDistanceX(p);
		double r = this.calcDistance(p);
		double f = this.calcForceExertedBy(p);
		double fx = (f * dx) / r;

		return fx;
	}

	public double calcForceExertedByY(Planet p) {
		double dy = this.calcDistanceY(p);
		double r = this.calcDistance(p);
		double f = this.calcForceExertedBy(p);
		double fy = (f * dy) / r;

		return fy;
	}

	public double calcNetForceExertedByX(Planet[] ps) {
		double fx = 0;
		for (Planet p : ps) {
			if (this.equals(p)) {
				continue;
			} else {
				fx += this.calcForceExertedByX(p);
			}
		}

		return fx;
	}

	public double calcNetForceExertedByY(Planet[] ps) {
		double fy = 0;
		for (Planet p : ps) {
			if (this.equals(p)) {
				continue;
			} else {
				fy += this.calcForceExertedByY(p);
			}
		}

		return fy;
	}

	public void update(double dt, double fx, double fy) {
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.xxVel = this.xxVel + dt * ax;
		this.yyVel = this.yyVel + dt * ay;
		this.xxPos = this.xxPos + dt * xxVel;
		this.yyPos = this.yyPos + dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
