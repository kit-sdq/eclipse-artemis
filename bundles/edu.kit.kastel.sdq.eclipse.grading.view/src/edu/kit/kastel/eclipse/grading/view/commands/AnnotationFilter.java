package edu.kit.kastel.eclipse.grading.view.commands;

import java.util.Arrays;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import edu.kit.kastel.eclipse.common.api.PreferenceConstants;
import edu.kit.kastel.eclipse.common.api.model.IMistakeType;
import edu.kit.kastel.eclipse.common.view.activator.CommonActivator;

public class AnnotationFilter extends ViewerFilter {
	private final boolean includeMessages;
	private String filter;

	public AnnotationFilter() {
		this.includeMessages = CommonActivator.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.SEARCH_IN_MISTAKE_MESSAGES);
	}

	public void setFilterString(String filter) {
		this.filter = filter.toLowerCase();
	}

	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (this.filter != null) {
			IMistakeType mistake = (IMistakeType) element;
			return matchesPart(mistake.getButtonText().toLowerCase(), this.filter)
					|| (this.includeMessages && matchesPart(mistake.getMessage().toLowerCase(), this.filter));
		} else {
			return true;
		}
	}

	private boolean matchesPart(String text, String filter) {
		return Arrays.stream(text.split(" ")).anyMatch(w -> w.startsWith(this.filter));
	}
}
