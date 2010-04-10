package com.ft.utils;

import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.Cell;
import jxl.DateCell;

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
	private static final byte STRING_ENCODE = HSSFWorkbook.ENCODING_UTF_16;

	private static final short LENGTH_DATE = (short) (DateUtil.dateFormate(
			new Date(), "yyyy-MM-dd HH:mm").length() * 260);

	private static Log log = LogFactory.getLog(ExcelUtil.class);

	private static final short LENGTH_STRING = (short) (10 * 260);

	public static HSSFWorkbook createExcleFile(ExcelVo vo) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(); // ÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩ
		String sheetName = vo.getSheetName();
		if (StringUtils.isEmpty(sheetName)) {
			sheetName = "firstSheet";
		}
		workbook.setSheetName(0, sheetName, STRING_ENCODE);

		int s = 0;
		if (vo.getFields() != null && vo.getFields().length > 0) {
			s = 1;
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
		f.setBoldweight(f.BOLDWEIGHT_BOLD);
		cs.setFont(f);
		cs.setBorderBottom(cs.BORDER_THIN);
		return cs;
	}

	private static HSSFCellStyle getDateStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		return cellStyle;
	}

	public static String getCellStringValue(HSSFRow row, int cellIndex) {
		HSSFCell cell = row.getCell((short) cellIndex);
		String re = "";
		if (cell != null) {
			try {
				re = cell.getRichStringCellValue().getString();
			} catch (RuntimeException e) {
				try {
					NumberFormat nf1 = NumberFormat.getInstance(Locale.CHINA);
					re = nf1.format(cell.getNumericCellValue());
				} catch (RuntimeException e1) {
					e1.printStackTrace();
				}
			}
		}
		return re;
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
				date = DateUtil.getDateDf(con,  "yyyyƒÍMM‘¬dd»’");
				if (date == null) {
					date = DateUtil.getDateDf(con, "yyyyƒÍMM‘¬dd»’");
				}
			}
		}
		return date;
	}

}
