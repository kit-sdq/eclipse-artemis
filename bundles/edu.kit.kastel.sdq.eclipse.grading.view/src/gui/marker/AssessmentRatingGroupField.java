package gui.marker;

import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;

public class AssessmentRatingGroupField extends MarkerField {

	@Override
	public String getValue(MarkerItem item) {
		return item.getAttributeValue("ratingGroup", "");
	}

}