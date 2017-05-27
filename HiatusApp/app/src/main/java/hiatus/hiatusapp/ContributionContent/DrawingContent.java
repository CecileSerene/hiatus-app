package hiatus.hiatusapp.ContributionContent;


import android.graphics.Canvas;
import android.view.View;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionDrawing;

/**
 * Created by Cecile on 24/05/2017.
 */

public class DrawingContent implements ContributionContent {

    private Canvas drawing;
    private String title;
    private ContributionContext context;

    public void DrawingContent(Canvas drawing, String title, ContributionDrawing context){
        this.drawing = drawing;
        this.title = title;
        this.context = context;
    }

    @Override
    public void display(View titleView, View drawingView) {
        //TODO

    }

    @Override
    public void sendToDatabase() {
        //TODO

    }
}
