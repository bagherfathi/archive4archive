package com.renhenet.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil {

	@SuppressWarnings("deprecation")
	private static final byte STRING_ENCODE = HSSFWorkbook.ENCODING_UTF_16;

	private static final short LENGTH_DATE = (short) (DateUtil.dateFormate(
			new Date(), "yyyy-MM-dd HH:mm").length() * 260);

	private static Log log = LogFactory.getLog(ExcelUtil.class);

	private static final short LENGTH_STRING = (short) (10 * 260);

	/**
	 * ��ȡ��
	 * 
	 * @param filepath
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Cell[]> getSheet(String filepath, int index) {
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(filepath));
		} catch (Exception e) {
			return null;
		}
		Sheet s = workbook.getSheet(index);
		int rows = s.getRows();
		ArrayList<Cell[]> l = new ArrayList();

		for (int i = 1; i < rows; i++) {
			Cell[] cells = s.getRow(i);
			if (cells.length < 1) {
				continue;
			}
			l.add(cells);
		}
		return l;
	}

	/**
	 * ��ȡ��
	 * 
	 * @param filepath
	 * @return
	 */
	public static List<Cell[]> getSheet(String filepath) {
		return getSheet(filepath, 0);
	}

	public static HSSFWorkbook createExcleFile(ExcelVo vo) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(); // ��������������
		String sheetName = vo.getSheetName();
		if (StringUtils.isEmpty(sheetName)) {
			sheetName = "firstSheet";
		}
		workbook.setSheetName(0, sheetName, STRING_ENCODE);

		int s = 0;
		if (vo.getFields() != null && vo.getFields().length > 0) {
			s = 1;
			// ����һ��
			HSSFRow row = sheet.createRow((short) 0);
			for (int i = 0; i < vo.getFields().length; i++) {
				String field = vo.getFields()[i];
				HSSFCell cell0 = row.createCell((short) i);
				cell0.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell0.setCellValue(field);
				cell0.setCellStyle(getFieldNameStyle(workbook));
			}
		}

		if (vo.getRows() != null && vo.getRows().size() > 0) {
			HSSFCellStyle dateStyle = getDateStyle(workbook);
			int l = vo.getRows().size();
			log.debug("now to create excel file, rows: " + l);
			int x = 0;
			boolean cellSizeInited = false;

			for (int i = s; i < s + l; i++) {
				if ((i % 500) == 0) {
					System.err.print(".");
				}
				Object[] os = vo.getRows().get(x);
				x++;
				HSSFRow row = sheet.createRow((short) i);
				for (int j = 0; j < os.length; j++) {
					Object o = os[j];
					if (o == null) {
						o = "";
					}
					HSSFCell cell = row.createCell((short) j);
					if (o instanceof Integer || o instanceof Float
							|| o instanceof Double) {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						Double d = new Double(o.toString());
						cell.setCellValue(d);

					} else if (o instanceof String) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setEncoding(STRING_ENCODE);
						cell.setCellValue((String) o);
						if (!cellSizeInited) {
							sheet.setColumnWidth((short) j, LENGTH_STRING);
						}

					} else if (o instanceof Date) {
						Date d = (Date) o;
						cell.setCellStyle(dateStyle);
						cell.setCellValue(d);
						if (!cellSizeInited) {
							sheet.setColumnWidth((short) j, LENGTH_DATE);
						}

					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setEncoding(STRING_ENCODE);
						cell.setCellValue(o.toString());
						if (!cellSizeInited) {
							sheet.setColumnWidth((short) j, LENGTH_STRING);
						}

					}
				}
				if (!cellSizeInited) {
					cellSizeInited = true;
				}
			}
		}
		return workbook;
	}

	private static HSSFCellStyle getFieldNameStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cs = workbook.createCellStyle();
		HSSFFont f = workbook.createFont();
		f.setColor((short) HSSFColor.BLUE.index);
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs.setFont(f);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		return cs;
	}

	private static HSSFCellStyle getDateStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();// �����µ�cell��ʽ
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));// ����cell��ʽΪ���Ƶ����ڸ�ʽ
		return cellStyle;
	}

	public static String getCellStringValue(Cell[] cells, int cellIndex) {
		String str = null;
		if (cells.length > cellIndex) {
			try {
				str = cells[cellIndex].getContents();
				if (str != null) {
					str = str.trim();
					str = str.replaceAll("\n", "");
				}
			} catch (Exception e) {
			}
		}
		return str;
	}

	public static Date getCellDateValue(Cell[] cells, int cellIndex) {
		Date date = null;
		if (cells.length > cellIndex) {
			try {
				DateCell datec11 = (DateCell) cells[cellIndex];
				date = datec11.getDate();
			} catch (Exception e) {
				String con = getCellStringValue(cells, cellIndex);
				date = DateUtil.getDateDf(con, "yyyy��MM��dd��");
				if (date == null) {
					date = DateUtil.getDateDf(con, "yyyy��MM��dd��");
				}
			}
		}
		return date;
	}

	/**
	 * just for test
	 * 
	 * @param args
	 */
	public static void main(String args[]) throws Exception {
		int count = 10000;
		if (args != null && args.length > 0) {
			count = Integer.parseInt(args[0]);
		}
		ExcelVo vo = new ExcelVo();
		String[] fields = new String[] { "����", "���", "���Float", "���Double",
				"ʱ��" };
		vo.setFields(fields);

		List<Object[]> rows = new ArrayList<Object[]>();
		for (int i = 0; i < count; i++) {

			String name = "��ǿ";
			int balance = 100;
			float balance1 = new Float(10.2);
			double balance12 = 105.3;
			Date time = new Date();
			Object[] os = new Object[] { name, balance, balance1, balance12,
					time };
			rows.add(os);
		}

		vo.setRows(rows);
		vo.setSheetName("��Ա��Ϣͳ��");

		HSSFWorkbook workbook = createExcleFile(vo);
		String xlsFile = "/home/greenli/test.xls";
		FileOutputStream fOut = new FileOutputStream(xlsFile);
		workbook.write(fOut);
		fOut.flush();
		fOut.close();
	}

}