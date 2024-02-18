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
import org.eclipse.lsp4j.adapters.CompletionItemDefaultsEditRangeTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.json.adapters.JsonElementTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * In many cases the items of an actual completion result share the same
 * value for properties like {@link CompletionItem#commitCharacters} or the range of a text
 * edit. A completion list can therefore define item defaults which will
 * be used if a completion item itself doesn't specify the value.
 * <p>
 * If a completion list specifies a default value and a completion item
 * also specifies a corresponding value the one from the item is used.
 * <p>
 * Servers are only allowed to return default values if the client
 * signals support for this via the {@link CompletionListCapabilities#itemDefaults}
 * capability.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class CompletionItemDefaults {
  /**
   * A default commit character set.
   */
  private List<String> commitCharacters;

  /**
   * A default edit range
   */
  @JsonAdapter(CompletionItemDefaultsEditRangeTypeAdapter.class)
  private Either<Range, InsertReplaceRange> editRange;

  /**
   * A default insert text format
   */
  private InsertTextFormat insertTextFormat;

  /**
   * A default insert text mode
   */
  private InsertTextMode insertTextMode;

  /**
   * A default data value.
   */
  @JsonAdapter(JsonElementTypeAdapter.Factory.class)
  private Object data;

  public CompletionItemDefaults() {
  }

  /**
   * A default commit character set.
   */
  public List<String> getCommitCharacters() {
    return this.commitCharacters;
  }

  /**
   * A default commit character set.
   */
  public void setCommitCharacters(final List<String> commitCharacters) {
    this.commitCharacters = commitCharacters;
  }

  /**
   * A default edit range
   */
  public Either<Range, InsertReplaceRange> getEditRange() {
    return this.editRange;
  }

  /**
   * A default edit range
   */
  public void setEditRange(final Either<Range, InsertReplaceRange> editRange) {
    this.editRange = editRange;
  }

  /**
   * A default insert text format
   */
  public InsertTextFormat getInsertTextFormat() {
    return this.insertTextFormat;
  }

  /**
   * A default insert text format
   */
  public void setInsertTextFormat(final InsertTextFormat insertTextFormat) {
    this.insertTextFormat = insertTextFormat;
  }

  /**
   * A default insert text mode
   */
  public InsertTextMode getInsertTextMode() {
    return this.insertTextMode;
  }

  /**
   * A default insert text mode
   */
  public void setInsertTextMode(final InsertTextMode insertTextMode) {
    this.insertTextMode = insertTextMode;
  }

  /**
   * A default data value.
   */
  public Object getData() {
    return this.data;
  }

  /**
   * A default data value.
   */
  public void setData(final Object data) {
    this.data = data;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("commitCharacters", this.commitCharacters);
    b.add("editRange", this.editRange);
    b.add("insertTextFormat", this.insertTextFormat);
    b.add("insertTextMode", this.insertTextMode);
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
    CompletionItemDefaults other = (CompletionItemDefaults) obj;
    if (this.commitCharacters == null) {
      if (other.commitCharacters != null)
        return false;
    } else if (!this.commitCharacters.equals(other.commitCharacters))
      return false;
    if (this.editRange == null) {
      if (other.editRange != null)
        return false;
    } else if (!this.editRange.equals(other.editRange))
      return false;
    if (this.insertTextFormat == null) {
      if (other.insertTextFormat != null)
        return false;
    } else if (!this.insertTextFormat.equals(other.insertTextFormat))
      return false;
    if (this.insertTextMode == null) {
      if (other.insertTextMode != null)
        return false;
    } else if (!this.insertTextMode.equals(other.insertTextMode))
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
    result = prime * result + ((this.commitCharacters== null) ? 0 : this.commitCharacters.hashCode());
    result = prime * result + ((this.editRange== null) ? 0 : this.editRange.hashCode());
    result = prime * result + ((this.insertTextFormat== null) ? 0 : this.insertTextFormat.hashCode());
    result = prime * result + ((this.insertTextMode== null) ? 0 : this.insertTextMode.hashCode());
    return prime * result + ((this.data== null) ? 0 : this.data.hashCode());
  }
}
