/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import com.google.gson.annotations.JsonAdapter;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Inlay hint information.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlayHint {
  /**
   * The position of this hint.
   */
  @NonNull
  private Position position;

  /**
   * The label of this hint. A human readable string or an array of
   * {@link InlayHintLabelPart} label parts.
   * <p>
   * *Note* that neither the string nor the label part can be empty.
   */
  @NonNull
  private Either<String, List<InlayHintLabelPart>> label;

  /**
   * The kind of this hint. Can be omitted in which case the client
   * should fall back to a reasonable default.
   */
  private InlayHintKind kind;

  /**
   * Optional text edits that are performed when accepting this inlay hint.
   * <p>
   * *Note* that edits are expected to change the document so that the inlay
   * hint (or its nearest variant) is now part of the document and the inlay
   * hint itself is now obsolete.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  private List<TextEdit> textEdits;

  /**
   * The tooltip text when you hover over this item.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  private Either<String, MarkupContent> tooltip;

  /**
   * Render padding before the hint.
   * <p>
   * Note: Padding should use the editor's background color, not the
   * background color of the hint itself. That means padding can be used
   * to visually align/separate an inlay hint.
   */
  private Boolean paddingLeft;

  /**
   * Render padding after the hint.
   * <p>
   * Note: Padding should use the editor's background color, not the
   * background color of the hint itself. That means padding can be used
   * to visually align/separate an inlay hint.
   */
  private Boolean paddingRight;

  /**
   * A data entry field that is preserved on a inlay hint between
   * a {@code textDocument/inlayHint} and a {@code inlayHint/resolve} request.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object data;

  public InlayHint() {
  }

  public InlayHint(@NonNull final Position position, @NonNull final Either<String, List<InlayHintLabelPart>> label) {
    this.position = Preconditions.<Position>checkNotNull(position, "position");
    this.label = Preconditions.<Either<String, List<InlayHintLabelPart>>>checkNotNull(label, "label");
  }

  /**
   * The position of this hint.
   */
  @NonNull
  public Position getPosition() {
    return this.position;
  }

  /**
   * The position of this hint.
   */
  public void setPosition(@NonNull final Position position) {
    this.position = Preconditions.checkNotNull(position, "position");
  }

  /**
   * The label of this hint. A human readable string or an array of
   * {@link InlayHintLabelPart} label parts.
   * <p>
   * *Note* that neither the string nor the label part can be empty.
   */
  @NonNull
  public Either<String, List<InlayHintLabelPart>> getLabel() {
    return this.label;
  }

  /**
   * The label of this hint. A human readable string or an array of
   * {@link InlayHintLabelPart} label parts.
   * <p>
   * *Note* that neither the string nor the label part can be empty.
   */
  public void setLabel(@NonNull final Either<String, List<InlayHintLabelPart>> label) {
    this.label = Preconditions.checkNotNull(label, "label");
  }

  public void setLabel(final String label) {
    if (label == null) {
      Preconditions.checkNotNull(label, "label");
      this.label = null;
      return;
    }
    this.label = Either.forLeft(label);
  }

  public void setLabel(final List<InlayHintLabelPart> label) {
    if (label == null) {
      Preconditions.checkNotNull(label, "label");
      this.label = null;
      return;
    }
    this.label = Either.forRight(label);
  }

  /**
   * The kind of this hint. Can be omitted in which case the client
   * should fall back to a reasonable default.
   */
  public InlayHintKind getKind() {
    return this.kind;
  }

  /**
   * The kind of this hint. Can be omitted in which case the client
   * should fall back to a reasonable default.
   */
  public void setKind(final InlayHintKind kind) {
    this.kind = kind;
  }

  /**
   * Optional text edits that are performed when accepting this inlay hint.
   * <p>
   * *Note* that edits are expected to change the document so that the inlay
   * hint (or its nearest variant) is now part of the document and the inlay
   * hint itself is now obsolete.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public List<TextEdit> getTextEdits() {
    return this.textEdits;
  }

  /**
   * Optional text edits that are performed when accepting this inlay hint.
   * <p>
   * *Note* that edits are expected to change the document so that the inlay
   * hint (or its nearest variant) is now part of the document and the inlay
   * hint itself is now obsolete.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public void setTextEdits(final List<TextEdit> textEdits) {
    this.textEdits = textEdits;
  }

  /**
   * The tooltip text when you hover over this item.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public Either<String, MarkupContent> getTooltip() {
    return this.tooltip;
  }

  /**
   * The tooltip text when you hover over this item.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public void setTooltip(final Either<String, MarkupContent> tooltip) {
    this.tooltip = tooltip;
  }

  public void setTooltip(final String tooltip) {
    if (tooltip == null) {
      this.tooltip = null;
      return;
    }
    this.tooltip = Either.forLeft(tooltip);
  }

  public void setTooltip(final MarkupContent tooltip) {
    if (tooltip == null) {
      this.tooltip = null;
      return;
    }
    this.tooltip = Either.forRight(tooltip);
  }

  /**
   * Render padding before the hint.
   * <p>
   * Note: Padding should use the editor's background color, not the
   * background color of the hint itself. That means padding can be used
   * to visually align/separate an inlay hint.
   */
  public Boolean getPaddingLeft() {
    return this.paddingLeft;
  }

  /**
   * Render padding before the hint.
   * <p>
   * Note: Padding should use the editor's background color, not the
   * background color of the hint itself. That means padding can be used
   * to visually align/separate an inlay hint.
   */
  public void setPaddingLeft(final Boolean paddingLeft) {
    this.paddingLeft = paddingLeft;
  }

  /**
   * Render padding after the hint.
   * <p>
   * Note: Padding should use the editor's background color, not the
   * background color of the hint itself. That means padding can be used
   * to visually align/separate an inlay hint.
   */
  public Boolean getPaddingRight() {
    return this.paddingRight;
  }

  /**
   * Render padding after the hint.
   * <p>
   * Note: Padding should use the editor's background color, not the
   * background color of the hint itself. That means padding can be used
   * to visually align/separate an inlay hint.
   */
  public void setPaddingRight(final Boolean paddingRight) {
    this.paddingRight = paddingRight;
  }

  /**
   * A data entry field that is preserved on a inlay hint between
   * a {@code textDocument/inlayHint} and a {@code inlayHint/resolve} request.
   */
  public Object getData() {
    return this.data;
  }

  /**
   * A data entry field that is preserved on a inlay hint between
   * a {@code textDocument/inlayHint} and a {@code inlayHint/resolve} request.
   */
  public void setData(final Object data) {
    this.data = data;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("position", this.position);
    b.add("label", this.label);
    b.add("kind", this.kind);
    b.add("textEdits", this.textEdits);
    b.add("tooltip", this.tooltip);
    b.add("paddingLeft", this.paddingLeft);
    b.add("paddingRight", this.paddingRight);
    b.add("data", this.data);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    InlayHint other = (InlayHint) obj;
    if (this.position == null) {
      if (other.position != null)
        return false;
    } else if (!this.position.equals(other.position))
      return false;
    if (this.label == null) {
      if (other.label != null)
        return false;
    } else if (!this.label.equals(other.label))
      return false;
    if (this.kind == null) {
      if (other.kind != null)
        return false;
    } else if (!this.kind.equals(other.kind))
      return false;
    if (this.textEdits == null) {
      if (other.textEdits != null)
        return false;
    } else if (!this.textEdits.equals(other.textEdits))
      return false;
    if (this.tooltip == null) {
      if (other.tooltip != null)
        return false;
    } else if (!this.tooltip.equals(other.tooltip))
      return false;
    if (this.paddingLeft == null) {
      if (other.paddingLeft != null)
        return false;
    } else if (!this.paddingLeft.equals(other.paddingLeft))
      return false;
    if (this.paddingRight == null) {
      if (other.paddingRight != null)
        return false;
    } else if (!this.paddingRight.equals(other.paddingRight))
      return false;
    if (this.data == null) {
      if (other.data != null)
        return false;
    } else if (!this.data.equals(other.data))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.position== null) ? 0 : this.position.hashCode());
    result = prime * result + ((this.label== null) ? 0 : this.label.hashCode());
    result = prime * result + ((this.kind== null) ? 0 : this.kind.hashCode());
    result = prime * result + ((this.textEdits== null) ? 0 : this.textEdits.hashCode());
    result = prime * result + ((this.tooltip== null) ? 0 : this.tooltip.hashCode());
    result = prime * result + ((this.paddingLeft== null) ? 0 : this.paddingLeft.hashCode());
    result = prime * result + ((this.paddingRight== null) ? 0 : this.paddingRight.hashCode());
    return prime * result + ((this.data== null) ? 0 : this.data.hashCode());
  }
}
