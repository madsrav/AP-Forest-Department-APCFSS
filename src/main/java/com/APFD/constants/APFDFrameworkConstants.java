package com.APFD.constants;

	public final class APFDFrameworkConstants {
		/**
		 * Private constructor to avoid external instantiation
		 */
		private APFDFrameworkConstants() {
		}

		private static final String PATHTORESOURCES = System.getProperty("user.dir") + "/src/test/resources";
		private static final String CONFIGFILEPATH = PATHTORESOURCES + "/config/configProperties.properties";
		private static final int EXPLICITWAIT=20;

		public static String getConfigFilePath() {
			return CONFIGFILEPATH;
		}

		public static int getExplicitWait() {
			return EXPLICITWAIT;
		}
	}