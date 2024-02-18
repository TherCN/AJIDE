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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Represents a collection of completion items to be presented in the editor.
 */
@SuppressWarnings("all")
public class CompletionList {
  /**
   * This list is not complete. Further typing should result in recomputing this list.
   */
  private boolean isIncomplete;

  /**
   * The completion items.
   */
  @NonNull
  private List<CompletionItem> items;

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
  private CompletionItemDefaults itemDefaults;

  public CompletionList() {
    this(new ArrayList<CompletionItem>());
  }

  public CompletionList(@NonNull final List<CompletionItem> items) {
    this.items = Preconditions.<List<CompletionItem>>checkNotNull(items, "items");
  }

  public CompletionList(final boolean isIncomplete, @NonNull final List<CompletionItem> items) {
    this(items);
    this.isIncomplete = isIncomplete;
  }

  public CompletionList(final boolean isIncomplete, @NonNull final List<CompletionItem> items, final CompletionItemDefaults itemDefaults) {
    this(isIncomplete, items);
    this.itemDefaults = itemDefaults;
  }

  /**
   * This list is not complete. Further typing should result in recomputing this list.
   */
  public boolean isIncomplete() {
    return this.isIncomplete;
  }

  /**
   * This list is not complete. Further typing should result in recomputing this list.
   */
  public void setIsIncomplete(final boolean isIncomplete) {
    this.isIncomplete = isIncomplete;
  }

  /**
   * The completion items.
   */
  @NonNull
  public List<CompletionItem> getItems() {
    return this.items;
  }

  /**
   * The completion items.
   */
  public void setItems(@NonNull final List<CompletionItem> items) {
    this.items = Preconditions.checkNotNull(items, "items");
  }

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
  public CompletionItemDefaults getItemDefaults() {
    return this.itemDefaults;
  }

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
  public void setItemDefaults(final CompletionItemDefaults itemDefaults) {
    this.itemDefaults = itemDefaults;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("isIncomplete", this.isIncomplete);
    b.add("items", this.items);
    b.add("itemDefaults", this.itemDefaults);
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
    CompletionList other = (CompletionList) obj;
    if (other.isIncomplete != this.isIncomplete)
      return false;
    if (this.items == null) {
      if (other.items != null)
        return false;
    } else if (!this.items.equals(other.items))
      return false;
    if (this.itemDefaults == null) {
      if (other.itemDefaults != null)
        return false;
    } else if (!this.itemDefaults.equals(other.itemDefaults))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.isIncomplete ? 1231 : 1237);
    result = prime * result + ((this.items== null) ? 0 : this.items.hashCode());
    return prime * result + ((this.itemDefaults== null) ? 0 : this.itemDefaults.hashCode());
  }
}
