package application.observerAndComposite;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CollisionData implements Observer{

	private final int width = 200;

	public TableView<ParticleComponent> dataTable;
	private TableColumn<ParticleComponent, Integer> column1;
	private TableColumn<ParticleComponent, Boolean> column2;

	public BarChart<String, Integer> barChart;
	private XYChart.Series<String, Integer> series;
	private XYChart.Data<String, Integer> collisionData;
	private XYChart.Data<String, Integer> noCollisionData;

	private int collisionCount=0;
	private int totalCount=0;


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

	private void createBarGraph() {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Collision");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Percent of Particles");
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(100);
		yAxis.setMinSize(0, 100);
		yAxis.setMaxSize(0, 100);
		yAxis.setAutoRanging(false);

		barChart = new BarChart(xAxis, yAxis);
		barChart.setMaxSize(150, 250);
		series = new XYChart.Series();
		series.setName("Particles");
		barChart.setLegendVisible(false);
		//	barChart.set

		collisionData = new XYChart.Data<>("Collision", (Integer)0);
		noCollisionData = new XYChart.Data<>("No Collision", (Integer)0);
		//		data.setValue(true);;
		series.getData().addAll(collisionData, noCollisionData);
		barChart.getData().add(series);
	}

	private void createDataTable() {
		dataTable = new TableView<>();
		dataTable.setMinSize(width, 250);
		dataTable.setMaxSize(width, 250);

		column1 = new TableColumn<>("Particle #");
		column1.setMinWidth(width/2);
		column1.setCellValueFactory(new PropertyValueFactory<>("particleNumber"));

		column2 = new TableColumn<>("Collision?");
		column2.setMinWidth(width/2);
		column2.setCellValueFactory(new PropertyValueFactory<>("collision"));

		dataTable.getColumns().add(column1);
		dataTable.getColumns().add(column2);

		column1.setSortType(TableColumn.SortType.DESCENDING);
		dataTable.getSortOrder().add(column1);
	}

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

	private void setTotalCount(ParticleComponent particle) {
		this.totalCount++;
		if (particle.isCollision())
			this.collisionCount++;
	}

	@Override
	public void update(ParticleComponent particle) {
		setTotalCount(particle);
		dataTable.getItems().add(particle);
		dataTable.sort();
		Double percent;
		percent = (double)this.collisionCount/this.totalCount*100;
		collisionData.setXValue("Collision");
		collisionData.setYValue(percent.intValue());

		percent = (double)(this.totalCount-this.collisionCount)/this.totalCount*100;
		noCollisionData.setXValue("No Collision");
		noCollisionData.setYValue(percent.intValue());
	}
}
