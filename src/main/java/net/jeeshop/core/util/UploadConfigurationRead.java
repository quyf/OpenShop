package net.jeeshop.core.util;

import java.io.File;

import java.io.FileInputStream;

import java.net.URI;

import java.net.URISyntaxException;

import java.util.Properties;

/**
 * 动态读取配置文件类
 * @author quyf
 */

public class UploadConfigurationRead {

	/**
	 * 属性文件全名,需要的时候请重新配置PFILE
	 */
	private static String PFILE = "upload.properties";

	/**
	 * 配置文件路径
	 */
	@SuppressWarnings("unused")
	private URI uri = null;

	/**
	 * 属性文件所对应的属性对象变量
	 */
	private long m_lastModifiedTime = 0;

	/**
	 * 对应于属性文件的文件对象变量
	 */
	private File mFile = null;

	/**
	 * 属性文件所对应的属性对象变量
	 */
	private Properties mProps = null;

	/**
	 * 唯一实例
	 */
	private static UploadConfigurationRead mInstance = new UploadConfigurationRead();
	/**
	 * 私有构造函数
	 * @throws URISyntaxException
	 */
	private UploadConfigurationRead() {
		try {
			m_lastModifiedTime = getFile().lastModified();
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + "file does not exist!");
			}
			mProps = new Properties();
			mProps.load(new FileInputStream(getFile()));
		} catch (URISyntaxException e) {
			System.err.println(PFILE + "文件路径不正确");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(PFILE + "文件读取异常");
			e.printStackTrace();
		}
	}

	/**
	 * 查找ClassPath路径获取文件
	 * @return File对象
	 * @throws URISyntaxException
	 */

	private File getFile() throws URISyntaxException {
		URI fileUri = this.getClass().getClassLoader().getResource(PFILE).toURI();
		mFile = new File(fileUri);
		return mFile;
	}

	/**
	 * 静态工厂方法
	 * @return 返回ConfigurationRead的单一实例
	 */

	public synchronized static UploadConfigurationRead getInstance() {
		return mInstance;
	}

	/**
	 * 读取一特定的属性项
	*/
	public String getConfigItem(String name, String defaultVal) {
		long newTime = mFile.lastModified();
		// 检查属性文件是否被修改
		if (newTime == 0) {
			// 属性文件不存在
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			mProps.clear();
			try {
				mProps.load(new FileInputStream(getFile()));
			} catch (Exception e) {
				System.err.println("文件重新读取异常");
				e.printStackTrace();
			}
		}

		m_lastModifiedTime = newTime;
		String val = mProps.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	/**
	 * 
	 * 读取一特定的属性项
	 * 
	 * @param name
	 *            属性项的项名
	 * @return 属性项的值（如此项存在）， 空（如此项不存在）
	 */

	public String getConfigItem(String name) {
		return getConfigItem(name, "");
	}

}
