package novel.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import novel.spider.NovelSiteEnum;

public final class NovelSpiderUtil {
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();
	static {
		init();
	}
	private NovelSpiderUtil() {}
	
	@SuppressWarnings("unchecked")
	private static void init() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(NovelSpiderUtil.class.getResourceAsStream("/Spider-Rule.xml"));
			Element root = doc.getRootElement();
			List<Element> sites = root.elements("site");
			for (Element site : sites) {
				List<Element> subs = site.elements();
				Map<String, String> subMap = new HashMap<>();
				for (Element sub : subs) {
					String name = sub.getName();
					String text = sub.getTextTrim();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ���Ӧ��վ�Ľ�������
	 */
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
		return CONTEXT_MAP.get(novelSiteEnum);
	}
	
	/**
	 * ����ļ��ϲ�Ϊһ���ļ����ϲ����򣺰��ļ����ָ�����
	 * @param path ����Ŀ¼���ø�Ŀ¼�µ������ı��ļ����ᱻ�ϲ��� mergeToFile
	 * @param mergeToFile ���ϲ����ı��ļ��������������Ϊnull,�ϲ�����ļ�������path/merge.txt
	 */
	public static void multiFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
		mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
		File[] files = new File(path).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
				if (o1Index > o2Index) {
					return 1;
				} else if (o1Index == o2Index){
					return 0;
				} else {
					return -1;
				}
			}
		});
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(mergeToFile), "UTF-8");
			for (File file : files) {
				BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));	
				String line = null;
				while ((line = bufr.readLine()) != null) {
					out.println(line);
				}
				bufr.close();
				
				if (deleteThisFile) {
					file.delete();
				}
			}
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		} finally {
			out.close();
		}
	}
	
	/**
	 * ��ȡ�鼮��״̬
	 * @param status
	 * @return
	 */
	public static int getNovelStatus(String status) {
		if (status.contains("����")) {
			return 1;
		} else if (status.contains("���") || status.contains("���")) {
			return 2;
		} else {
			throw new RuntimeException ("��֧�ֵ��鼮״̬��" + status);
		}
	}
	
	/**
	 * ��ʽ�������ַ���Ϊ���ڶ���
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr, String pattern) throws ParseException {
		if ("MM-dd".equals(pattern)) {
			pattern = "yyyy-MM-dd";
			dateStr = getDateField(Calendar.YEAR) + "-" + dateStr;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	/**
	 * ��ȡ��ʱ�̵��ַ���
	 * @param field
	 * @return
	 */
	public static String getDateField(int field) {
		Calendar cal = new GregorianCalendar();
		return cal.get(field) + "";
	}
}