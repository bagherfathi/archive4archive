<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="org.jfree.chart.*,
org.jfree.chart.plot.PiePlot,
org.jfree.data.general.DefaultPieDataset,
org.jfree.chart.servlet.ServletUtilities,
java.awt.*"%>
<%
//设置数据集
DefaultPieDataset dataset = new DefaultPieDataset();
dataset.setValue("初中高级程序员", 0.55);
dataset.setValue("项目经理", 0.1);
dataset.setValue("系统分析师", 0.1);
dataset.setValue("软件架构师", 0.1);
dataset.setValue("其他", 0.2);
//通过工厂类生成JFreeChart对象
JFreeChart chart = ChartFactory.createPieChart3D("IT行业职业分布图", dataset, true, false, false);
PiePlot pieplot = (PiePlot) chart.getPlot();
pieplot.setLabelFont(new Font("宋体", 0, 12));
//没有数据的时候显示的内容
pieplot.setNoDataMessage("无数据显示");
pieplot.setCircular(false);

pieplot.setLabelGap(0.02D);
String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
%>
<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= filename %>">
