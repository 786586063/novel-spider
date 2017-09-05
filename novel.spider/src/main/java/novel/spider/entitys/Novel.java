package novel.spider.entitys;

import java.io.Serializable;
import java.util.Date;

/**
 * С˵ʵ��
 * @author LiuKeFeng
 * @date   2016��10��14��
 */
public class Novel implements Serializable {
	private static final long serialVersionUID = 4834523404092493662L;
	/** ���� */
	private String name;
	/** ������ */
	private String author;
	/** С˵������ */
	private String url;
	/** С˵��������������棬�������� */
	private String type;
	/** ���һ�µ��½��� */
	private String lastUpdateChapter;
	/** ���һ�µ����� */
	private String lastUpdateChapterUrl;
	/** С˵�����µ�ʱ�� */
	private Date lastUpdateTime;
	/** С˵��״̬��1 ���� 2 ��� */
	private int status;
	/** ����������ĸ */
	private char firstLetter;
	/** С˵ƽ̨��id */
	private int platformId;
	/** �ⱾС˵�洢���������ݿ��ʱ�� */
	private Date addTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLastUpdateChapter() {
		return lastUpdateChapter;
	}
	public void setLastUpdateChapter(String lastUpdateChapter) {
		this.lastUpdateChapter = lastUpdateChapter;
	}
	public String getLastUpdateChapterUrl() {
		return lastUpdateChapterUrl;
	}
	public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
		this.lastUpdateChapterUrl = lastUpdateChapterUrl;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public char getFirstLetter() {
		return firstLetter;
	}
	public void setFirstLetter(char firstLetter) {
		this.firstLetter = firstLetter;
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "Novel [name=" + name + ", author=" + author + ", url=" + url + ", type=" + type + ", lastUpdateChapter="
				+ lastUpdateChapter + ", lastUpdateChapterUrl=" + lastUpdateChapterUrl + ", lastUpdateTime="
				+ lastUpdateTime + ", status=" + status + ", firstLetter=" + firstLetter + ", platformId=" + platformId
				+ ", addTime=" + addTime + "]";
	}
}
