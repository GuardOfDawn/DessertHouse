package dessert.action.storeManager;

import java.awt.Font;
import java.sql.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.remoteService.saleperformance.SaleperformanceService;
import dessert.utility.DayTransformer;

@Controller
public class PopularProductAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SaleperformanceService saleperformanceManage;
	
	private JFreeChart chart;
	
	public String execute(){
		if(session.get("userId")!=null){
			this.chart = formChart();
			return SUCCESS;
		}
		else{
			//user has not logged in
			return INPUT;
		}
	}
	
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public JFreeChart getChart(){
		return this.chart;
	}
	
	private JFreeChart formChart(){
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		String dateString = request.getParameter("date");
		Date date = null;
		if(dateString!=null&&(!dateString.equals("null"))){
			date = DayTransformer.transform(dateString);
		}
		else{
			date = new Date(System.currentTimeMillis());
		}
		String[][] storeSellingDis = saleperformanceManage.getTotalPopProductDis(DayTransformer.transformToMonth(date));
		for(int i=0;i<storeSellingDis.length;i++){
			data.setValue(Double.parseDouble(storeSellingDis[i][1]), storeSellingDis[i][0], storeSellingDis[i][0]);
		}
		JFreeChart barChart = ChartFactory.createBarChart("热卖产品销量图", "产品名称", 
                "热卖数量", data, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = (CategoryPlot) barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        //显示条目标签
        renderer.setBaseItemLabelsVisible(true);
        //设置条目标签生成器,在JFreeChart1.0.6之前可以通过renderer.setItemLabelGenerator(CategoryItemLabelGenerator generator)方法实现，但是从版本1.0.6开始有下面方法代替
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        //设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		barChart.getLegend().setItemFont(new Font("黑体",Font.BOLD,15));  //设置引用标签字体
		barChart.getTitle().setFont(new Font("华文行楷",Font.BOLD,32));
		return barChart;
	}

}