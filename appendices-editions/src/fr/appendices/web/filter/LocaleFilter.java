package fr.appendices.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import fr.appendices.util.StringAndSet;


public class LocaleFilter implements Filter {

	private FilterConfig filterConfig;
	private static final Logger logger = Logger.getLogger(LocaleFilter.class.getName());

	private String defaultLocale = "en";
	private String supportedLocales = "en|fr";
	private Set<String> localeSet = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		if (!(request instanceof HttpServletRequest)) {
			filterChain.doFilter(request, response);
			return;
		}

		Locale locale = request.getLocale();
		//logger.info("Locale of the request is [" + locale.toString() + "]");

		String language = request.getParameter("language");

		if (language != null && language.length() > 0)
			if (localeSet.contains(language)) {
				Locale newLocale = new Locale(language);
				logger.info("Locale change [" + newLocale.toString() + "] succeed on user action");
				Config.set(((HttpServletRequest) request).getSession(), Config.FMT_LOCALE, newLocale);
			} else {
				logger.info("Locale change [" + language + "] failed on user action due to unsupported language");
			}

		// set language attribute in the request which is the language to be used
		((HttpServletRequest) request).getSession().setAttribute("language", language);
		
		filterChain.doFilter(request, response);
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void init(FilterConfig filterConfig) {
		logger.info("Call init on LocaleFilter");
		String cfgDefaultLocale = filterConfig.getInitParameter("defaultLocale");
		this.defaultLocale = (cfgDefaultLocale != null && cfgDefaultLocale.length() > 0) ? cfgDefaultLocale : this.defaultLocale;
		String cfgSupportedLocales = filterConfig.getInitParameter("supportedLocales");
		this.supportedLocales = (cfgSupportedLocales != null && cfgSupportedLocales.length() > 0) ? cfgSupportedLocales : this.supportedLocales;
		this.localeSet = StringAndSet.string2Set(this.supportedLocales);
		this.filterConfig = filterConfig;
		logger.info("DefaultLocale is [" + this.defaultLocale + "]");
		logger.info("SupportedLocales are [" + this.supportedLocales + "]");
	}

	public void destroy() {
	}

}
