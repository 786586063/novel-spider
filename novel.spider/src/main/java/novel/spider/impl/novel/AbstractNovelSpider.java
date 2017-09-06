package novel.spider.impl.novel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.configuration.NovelSiteEnum;
import novel.spider.configuration.SiteDefinition;
import novel.spider.entitys.Novel;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderUtil;

/**
 * һ�������С˵�б�ץȡ���Ѿ�ʵ���˽���trԪ�صķ���
 * @author LiuKeFeng
 * @date   2016��10��14��
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {
	protected Element nextPageElement;
	/** ��һҳ��url */
	protected String nextPage;
	/**
	 * Ĭ�ϵ�ץȡ���������᳢�� {@link INovelSpider#MAX_TRY_TIMES} ������
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected Elements getsTr(String url) throws Exception {
		return getsTr(url, INovelSpider.MAX_TRY_TIMES);
	}
	/**
	 * ��ָ����������ʽȥ������Ӧ��ҳ
	 * @param url
	 * @param maxTryTimes ���Ϊnull�� �� Ĭ���� {@link INovelSpider#MAX_TRY_TIMES}
	 * @return
	 * @throws Exception
	 */
	protected Elements getsTr(String url, Integer maxTryTimes) throws Exception {
		maxTryTimes = maxTryTimes == null ? INovelSpider.MAX_TRY_TIMES : maxTryTimes;
		Elements trs = null;
		for (int i = 0; i < maxTryTimes ; i++) {
			try {
				String result = super.crawl(url);
				SiteDefinition siteDefinition = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
				String novelSelector = siteDefinition.getNovelSelector();
				if (novelSelector == null) throw new RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "Ŀǰ��֧��ץȡС˵�б�");
				Document doc = Jsoup.parse(result);
				doc.setBaseUri(url);
				trs = doc.select(novelSelector);
				
				String nextPageSelector = siteDefinition.getNovelNextPageSelector();
				if (nextPageSelector != null) {
					Elements nextPageElements = doc.select(nextPageSelector);
					nextPageElement = nextPageElements == null ? null : nextPageElements.first();
					
					if (nextPageElement != null) {
						nextPage = nextPageElement.absUrl("href");
					} else {
						nextPage = "";
					}
				}
				return trs;
			} catch (Exception e) {
				
			}
		}
		throw new RuntimeException(url + ",������" + maxTryTimes + "����Ȼ����ʧ���ˣ�");
	}
	
	@Override
	public boolean hasNext() {
		return !nextPage.isEmpty();
	}

	@Override
	public String next() {
		return nextPage;
	}
	@Override
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
		nextPage = firstPage;
		return new NovelIterator(maxTryTimes);
	}
	/**
	 * һ����������ר�����ڶ�С˵��վ�����б�ץȡ
	 * @author LiuKeFeng
	 * @date   2016��10��16��
	 */
	private class NovelIterator implements Iterator<List<Novel>> {
		private Integer maxTryTimes;
		public NovelIterator(Integer maxTryTimes) {
			this.maxTryTimes = maxTryTimes;
		}
		@Override
		public boolean hasNext() {
			return AbstractNovelSpider.this.hasNext();
		}
		@Override
		public List<Novel> next() {
			List<Novel> novels = getsNovel(nextPage, maxTryTimes);
			return novels;
		}
		@Override
		public void remove() {
			
		}
	}
}
