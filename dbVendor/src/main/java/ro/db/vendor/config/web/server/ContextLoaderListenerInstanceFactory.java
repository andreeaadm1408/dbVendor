package ro.db.vendor.config.web.server;

import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;
import org.springframework.web.context.ContextLoaderListener;

public class ContextLoaderListenerInstanceFactory implements
		InstanceFactory<ContextLoaderListener> {

	public InstanceHandle<ContextLoaderListener> createInstance()
			throws InstantiationException {
		return new ContextLoaderListenerInstanceHandle();
	}

	class ContextLoaderListenerInstanceHandle implements
			InstanceHandle<ContextLoaderListener> {

		@Override
		public ContextLoaderListener getInstance() {
			return new ContextLoaderListener(
					SupportAssistApplicationContext.INSTANCE.getPersistenceContext());
		}

		@Override
		public void release() {
		}

	}

}
