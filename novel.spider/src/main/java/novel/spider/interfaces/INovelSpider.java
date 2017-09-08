package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entitys.Novel;

/**
 * ��ȡĳ��վ���С˵�б�
 * @author LiuKeFeng
 * @date   2016��10��14��
 */
public interface INovelSpider {
	/** ץȡĳһ��ҳ��ʱ���ĳ��Դ���3 */
	public static final int MAX_TRY_TIMES = 3;
	/**
	 * ����һ��URL���Ҿ͸���һ�ѵ�С˵ʵ��
	 * @param url
	 * @param maxTryTimes ��ҳ���ص�������������ʧ�����ԵĴ�����
	 * @return
	 */
	public List<Novel> getsNovel(String url, Integer maxTryTimes);
	
	public boolean hasNext();
	
	public String next();
	
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);
}
