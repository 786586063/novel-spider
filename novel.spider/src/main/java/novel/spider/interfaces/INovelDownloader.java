package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownloader {
	/**
	 * ����˵�����ص�D:/novel/biquge.tw/��������/��������.txt
	 * @param url ���url��ָĳ��С˵���½��б�ҳ��
	 * @return
	 */
	public String download(String url, Configuration config);
}
