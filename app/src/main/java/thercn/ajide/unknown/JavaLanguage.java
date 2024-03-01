package thercn.ajide.unknown;
import android.os.Bundle;
import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager;
import io.github.rosemoe.sora.lang.completion.CompletionCancelledException;
import io.github.rosemoe.sora.lang.completion.CompletionPublisher;
import io.github.rosemoe.sora.lang.format.Formatter;
import io.github.rosemoe.sora.lang.smartEnter.NewlineHandler;
import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.text.ContentReference;
import io.github.rosemoe.sora.widget.SymbolPairMatch;

public class JavaLanguage implements Language {

    public static final String TAG = "JavaLanguage";

	@Override
	public void requireAutoComplete(ContentReference contentReference,
									CharPosition charPosition,
									CompletionPublisher completionPublisher,
									Bundle bundle) throws CompletionCancelledException {
	}

	@Override
	public int getInterruptionLevel() {
		return 0;
	}

	@Override
	public NewlineHandler[] getNewlineHandlers() {
		return null;
	}

	@Override
	public AnalyzeManager getAnalyzeManager() {
		return null;
	}

	@Override
	public Formatter getFormatter() {
		return null;
	}

	@Override
	public SymbolPairMatch getSymbolPairs() {
		return null;
	}

	@Override
	public void destroy() {
	}

	@Override
	public boolean useTab() {
		return false;
	}

	@Override
	public int getIndentAdvance(ContentReference contentReference, int p, int p1) {
		return 0;
	}


}
