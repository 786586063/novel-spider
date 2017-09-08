package novel.spider.factory;

import novel.spider.configuration.NovelSiteEnum;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

public final class ChapterSpiderFactory {
	private ChapterSpiderFactory() {}
	
	/**
	 * ͨ��������url������һ��ʵ����IChapterSpider�ӿڵ�ʵ����
	 * @param url
	 * @return
	 */
	public static IChapterSpider getChapterSpider(String url) {
		NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
		IChapterSpider chapterSpider = null;
		switch (novelSiteEnum) {
		case Bxwx :
			chapterSpider = new BxwxChapterSpider(); break;
		case DingDianXiaoShuo:
		case BiQuGe:
		case KanShuZhong :
		chapterSpider = new DefaultChapterSpider(); break;
		}
		return chapterSpider;
	}
}