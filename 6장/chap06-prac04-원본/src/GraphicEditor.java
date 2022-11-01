class Shape {
	public void draw() { System.out.println("Shape"); }
}
class Circle extends Shape {
	public void draw() { System.out.println("Circle"); }
}
public class GraphicEditor {
	public static void main(String[] args) {
		Shape shape = new Circle();
		shape.draw();
	}
}