package cn.wjb114514.Adapter.ObjectAdapter;


import org.springframework.web.servlet.DispatcherServlet;

public class SpringMVCSource {
    public static void main(String[] args) {

    }
}

/**
 *protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
 *      // 获取tomcat封装好的请求对象
 * 		HttpServletRequest processedRequest = request;
 * 	    // 获取针对此请求的执行链
 * 		HandlerExecutionChain mappedHandler = null;
 * 	    // 判断是不是文件上传请求
 * 		boolean multipartRequestParsed = false;
 *
 * 		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
 *
 * 		try {
 * 			ModelAndView mv = null;
 * 			Exception dispatchException = null;
 *
 * 			try {
 * 				processedRequest = checkMultipart(request);
 * 				multipartRequestParsed = (processedRequest != request);
 *
 * 				// Determine handler for the current request.
 *
 * 			    -- 1.从容器里取出handlerMapping对象，用于匹配请求路径和处理器的映射
 * 			    -- 于是，mappedHandler就是此请求对象request对应的处理器controller
 * 			    -- 简单来说，handlerMapping建立了请求url和处理器controller的映射。我们的请求对象可以解析出url，进而获取相应controller
 * 				mappedHandler = getHandler(processedRequest);
 * 				if (mappedHandler == null) {
 * 					noHandlerFound(processedRequest, response);
 * 					return;
 *                                }
 *
 * 				// Determine handler adapter for the current request.
 *
 * 		        -- 通过刚才获取到的controller。拿到一个适配器
 * 		        -- HandlerAdapter是一个接口，其下很多实现子类
 * 		        {
 *                  public class HttpRequestHandlerAdapter implements HandlerAdapter
 *                  public class SimpleServletHandlerAdapter implements HandlerAdapter
 * 		        }
 *
 ---------------------------------------------------------------------------------------------------------------------------
 protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
 if (this.handlerAdapters != null) {
    // 怎么拿到适配器：遍历当前所有的适配器，看看哪个支持就拿回来哪个适配器
    for (HandlerAdapter adapter : this.handlerAdapters) {
    if (adapter.supports(handler)) {
    return adapter;
        }
    }
 }
 throw new ServletException("No adapter for handler [" + handler + "]
 : The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
 }
 HandlerAdapter的实现子类，使得每一种Controller都有一宗对应的适配器实现类，每种Controller都有不同的实现方式

 ---------------------------------------------------------------------------------------------------------------------------
 * 				HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
 *
 * 				// Process last-modified header, if supported by the handler.
 * 				String method = request.getMethod();
 * 				boolean isGet = HttpMethod.GET.matches(method);
 * 				if (isGet || HttpMethod.HEAD.matches(method)) {
 * 					long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
 * 					if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
 * 						return;
 *                    }
 *                }
 *
 * 				if (!mappedHandler.applyPreHandle(processedRequest, response)) {
 * 					return;
 *                }
 *
 * 				// Actually invoke the handler.
 * 			    // 通过适配器调用handler[controller]的处理方法，获得视图对象。
 * 				mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
 *
 * 				if (asyncManager.isConcurrentHandlingStarted()) {
 * 					return;
 *                }
 *
 * 				applyDefaultViewName(processedRequest, mv);
 * 				mappedHandler.applyPostHandle(processedRequest, response, mv);* 			}
 * 			catch (Exception ex) {
 * 				dispatchException = ex;
 * 			}
 * 			catch (Throwable err) {
 * 				// As of 4.3, we're processing Errors thrown from handler methods as well,
 * 				// making them available for @ExceptionHandler methods and other scenarios.
 * 				dispatchException = new NestedServletException("Handler dispatch failed", err);
 * 			}
 * 			processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchExcepti        ;
 * 		}
 * 		catch (Exception ex) {
 * 			triggerAfterCompletion(processedRequest, response, mappedHandler,         ;
 * 		}
 * 		catch (Throwable err) {
 * 			triggerAfterCompletion(processedRequest, response, mappedHandler,
 * 					new NestedServletException("Handler processing failed", er        ;
 * 		}
 * 		finally {
 * 			if (asyncManager.isConcurrentHandlingStarted()) {
 * 				// Instead of postHandle and afterCompletion
 * 				if (mappedHandler != null) {
 * 					mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
 * 				}
 * 			}
 * 			else {
 * 				// Clean up any resources used by a multipart request.
 * 				if (multipartRequestParsed) {
 * 					cleanupMultipart(processedRequest);
 * 				}
 *                    }* 		}
 * 	}
 */