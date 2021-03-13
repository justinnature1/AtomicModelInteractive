package application.observerAndComposite;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class handles the creation of a data table and bar chart that observe and track data on alpha particles.
 * A great candidate for further encapsulation by separating the GUI elements from the data elements possibly using
 * an observable list.
 * @author Justin Keller
 */
public class CollisionData implements Observer{

	private final int width = 200;//Width of the data table and bar graph.

	//Variables used by the TableView
	public TableView<ParticleComponent> dataTable;
	private TableColumn<ParticleComponent, Integer> column1;
	private TableColumn<ParticleComponent, Boolean> column2;

	//Varibales used by the BarChart
	public BarChart<String, Integer> barChart;
	private XYChart.Series<String, Integer> series;
	private XYChart.Data<String, Integer> collisionData;
	private XYChart.Data<String, Integer> noCollisionData;

	//Variables used to track data
	private int collisionCount=0;
	private int totalCount=0;

	/**
	 * Creates the bar chart and table view to track data.  This class can observe alpha particles.
	 * @param dataPane A reference to a blank TabPane the table and graph should be added to.
	 */
	public CollisionData (TabPane dataPane) {
		createDataTable();
		createBarGraph();

		TabPane tabPane = dataPane;
		tabPane.setPrefSize(width, 242);

		Tab tab1 = new Tab("Data Chart", this.dataTable);
		Tab tab2 = new Tab("Percent Hit"  , this.barChart);

		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
	}

	/**
	 * Creates a BarChart that monitors the percentage of particles that collided and those that didn't.
	 */
	private void createBarGraph() {
		//Create X Axis
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Particle Collision?");

		//Create Y Axis and limits the axis to 0-100 (percentages) 
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Percent of Particles");
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(100);
		yAxis.setMinSize(0, 100);
		yAxis.setMaxSize(0, 100);
		yAxis.setAutoRanging(false);

		//Creates the BarChart and sets the size.
		barChart = new BarChart(xAxis, yAxis);
		barChart.setMaxSize(150, 250);
		
		//Creates data series but hides the legend since only one series is created.
		series = new XYChart.Series();
		barChart.setLegendVisible(false);

		//Adds initial data to the bar chart (0% for both categories)
		collisionData = new XYChart.Data<>("Collision", (Integer)0);
		noCollisionData = new XYChart.Data<>("No Collision", (Integer)0);
		series.getData().addAll(collisionData, noCollisionData);
		barChart.getData().add(series);
	}

	/**
	 * Creates a TableView that displays the raw data for each particle number and whether it collided or not.
	 */
	private void createDataTable() {
		// Creates TableView and sets size
		dataTable = new TableView<>();
		dataTable.setMinSize(width, 250);
		dataTable.setMaxSize(width, 250);

		// Creates first column to track particle number
		column1 = new TableColumn<>("Particle #");
		column1.setMinWidth(width/2);
		column1.setCellValueFactory(new PropertyValueFactory<>("particleNumber"));

		//Creates second column that tracks collisions
		column2 = new TableColumn<>("Collision?");
		column2.setMinWidth(width/2);
		column2.setCellValueFactory(new PropertyValueFactory<>("collision"));

		//Adds columns to TableView
		dataTable.getColumns().add(column1);
		dataTable.getColumns().add(column2);

		//Sorts the column 1 in descending order so the most recent particle is on top.
		column1.setSortType(TableColumn.SortType.DESCENDING);
		dataTable.getSortOrder().add(column1);
	}

	/**
	 * Clears data table, bar chart, and data. Sets info to initial values.
	 */
	public void clearData() {
		this.dataTable.getItems().clear();
		this.collisionData.setXValue("true");
		this.collisionData.setYValue(0);
		this.noCollisionData.setXValue("false");
		this.noCollisionData.setYValue(0);	
		this.collisionCount = 0;
		this.totalCount = 0;
	}

	public int getTotalCount() {
		return this.totalCount;
	}
	
	public int getCollisionCount() {
		return this.collisionCount;
	}

	/**
	 * Increments the particle count and increments the collision count if the particle collided.
	 * @param particle A reference to the particle containing data to count
	 */
	private void setTotalCount(ParticleComponent particle) {
		this.totalCount++;
		if (particle.isCollision())
			this.collisionCount++;
	}

	/**
	 * Updates the data, TableView, and BarChart when a particle notifies this class.
	 * @param particle Reference to the particle that notified this observer.
	 * @Override
	 */
	public void update(ParticleComponent particle) {
		//Updates data
		setTotalCount(particle);
		
		//Updates TableView
		dataTable.getItems().add(particle);
		dataTable.sort();
		
		//Updates BarChart Collision Bar
		Double percent;
		percent = (double)this.collisionCount/this.totalCount*100;
		collisionData.setXValue("Collision");
		collisionData.setYValue(percent.intValue());

		//Updates BarChart No Collision Bar
		percent = (double)(this.totalCount-this.collisionCount)/this.totalCount*100;
		noCollisionData.setXValue("No Collision");
		noCollisionData.setYValue(percent.intValue());
	}
}
