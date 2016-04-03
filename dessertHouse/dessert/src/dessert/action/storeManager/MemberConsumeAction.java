package dessert.action.storeManager;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.action.BaseAction;
import dessert.remoteService.saleperformance.SaleperformanceService;
import dessert.utility.DayTransformer;

@Controller
public class MemberConsumeAction extends BaseAction {

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
		DefaultPieDataset data = new DefaultPieDataset();
		String dateString = request.getParameter("date");
		Date date = null;
		if(dateString!=null&&(!dateString.equals("null"))){
			date = DayTransformer.transform(dateString);
		}
		else{
			date = new Date(System.currentTimeMillis());
		}
		String[][] consumeDis = saleperformanceManage.getMemberConsumeDis(DayTransformer.transformToMonth(date));
		for(int i=0;i<consumeDis.length;i++){
			data.setValue(consumeDis[i][0], Integer.valueOf(consumeDis[i][1]));
		}
		JFreeChart pieChart = ChartFactory.createPieChart("会员消费状况分布图", data, true, true, false);
		PiePlot piePlot = (PiePlot)pieChart.getPlot();
		piePlot.setBackgroundPaint(Color.WHITE);
		piePlot.setLabelFont(new Font("黑体",Font.BOLD,15));
		resetPlot(piePlot);
		pieChart.getLegend().setItemFont(new Font("黑体",Font.BOLD,15));  //设置引用标签字体
		pieChart.getTitle().setFont(new Font("华文行楷",Font.BOLD,32));
		return pieChart;
	}

	private static void resetPlot(PiePlot pieplot){
		String unitStyle = "{0}={1}({2})";
		  
		pieplot.setNoDataMessage("没有对应的数据，请重新查询");
		pieplot.setNoDataMessageFont(new Font("华文行楷",Font.BOLD,21));
		pieplot.setNoDataMessagePaint(Color.BLUE);
		  
		//设置图例显示样式
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
		        unitStyle,
		        NumberFormat.getNumberInstance(),
		        new DecimalFormat("0.00%")));
		//设置引用标签显示样式
		pieplot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
		       unitStyle,
		       NumberFormat.getNumberInstance(),
		       new DecimalFormat("0.00%")));
	}

}
