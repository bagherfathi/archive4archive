<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="org.jfree.chart.*,
org.jfree.chart.plot.PiePlot,
org.jfree.data.general.DefaultPieDataset,
org.jfree.chart.servlet.ServletUtilities,
java.awt.*"%>
<%
//�������ݼ�
DefaultPieDataset dataset = new DefaultPieDataset();
dataset.setValue("���и߼�����Ա", 0.55);
dataset.setValue("��Ŀ����", 0.1);
dataset.setValue("ϵͳ����ʦ", 0.1);
dataset.setValue("����ܹ�ʦ", 0.1);
dataset.setValue("����", 0.2);
//ͨ������������JFreeChart����
JFreeChart chart = ChartFactory.createPieChart3D("IT��ҵְҵ�ֲ�ͼ", dataset, true, false, false);
PiePlot pieplot = (PiePlot) chart.getPlot();
pieplot.setLabelFont(new Font("����", 0, 12));
//û�����ݵ�ʱ����ʾ������
pieplot.setNoDataMessage("��������ʾ");
pieplot.setCircular(false);

pieplot.setLabelGap(0.02D);
String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);
String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
%>
<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#<%= filename %>">
