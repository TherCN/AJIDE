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

import java.util.List;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Completion options.
 */
@SuppressWarnings("all")
public class CompletionOptions extends AbstractWorkDoneProgressOptions {
  /**
   * The server provides support to resolve additional information for a completion item.
   */
  private Boolean resolveProvider;

  /**
   * The characters that trigger completion automatically.
   */
  private List<String> triggerCharacters;

  /**
   * The list of all possible characters that commit a completion. This field
   * can be used if clients don't support individual commit characters per
   * completion item. See client capability
   * {@link CompletionItemCapabilities#commitCharactersSupport}.
   * <p>
   * If a server provides both {@code allCommitCharacters} and commit characters on
   * an individual completion item the ones on the completion item win.
   * <p>
   * Since 3.2.0
   */
  private List<String> allCommitCharacters;

  /**
   * The server supports the following {@link CompletionItem} specific capabilities.
   * <p>
   * Since 3.17.0
   */
  private CompletionItemOptions completionItem;

  public CompletionOptions() {
  }

  public CompletionOptions(final Boolean resolveProvider, final List<String> triggerCharacters) {
    this.resolveProvider = resolveProvider;
    this.triggerCharacters = triggerCharacters;
  }

  /**
   * The server provides support to resolve additional information for a completion item.
   */
  public Boolean getResolveProvider() {
    return this.resolveProvider;
  }

  /**
   * The server provides support to resolve additional information for a completion item.
   */
  public void setResolveProvider(final Boolean resolveProvider) {
    this.resolveProvider = resolveProvider;
  }

  /**
   * The characters that trigger completion automatically.
   */
  public List<String> getTriggerCharacters() {
    return this.triggerCharacters;
  }

  /**
   * The characters that trigger completion automatically.
   */
  public void setTriggerCharacters(final List<String> triggerCharacters) {
    this.triggerCharacters = triggerCharacters;
  }

  /**
   * The list of all possible characters that commit a completion. This field
   * can be used if clients don't support individual commit characters per
   * completion item. See client capability
   * {@link CompletionItemCapabilities#commitCharactersSupport}.
   * <p>
   * If a server provides both {@code allCommitCharacters} and commit characters on
   * an individual completion item the ones on the completion item win.
   * <p>
   * Since 3.2.0
   */
  public List<String> getAllCommitCharacters() {
    return this.allCommitCharacters;
  }

  /**
   * The list of all possible characters that commit a completion. This field
   * can be used if clients don't support individual commit characters per
   * completion item. See client capability
   * {@link CompletionItemCapabilities#commitCharactersSupport}.
   * <p>
   * If a server provides both {@code allCommitCharacters} and commit characters on
   * an individual completion item the ones on the completion item win.
   * <p>
   * Since 3.2.0
   */
  public void setAllCommitCharacters(final List<String> allCommitCharacters) {
    this.allCommitCharacters = allCommitCharacters;
  }

  /**
   * The server supports the following {@link CompletionItem} specific capabilities.
   * <p>
   * Since 3.17.0
   */
  public CompletionItemOptions getCompletionItem() {
    return this.completionItem;
  }

  /**
   * The server supports the following {@link CompletionItem} specific capabilities.
   * <p>
   * Since 3.17.0
   */
  public void setCompletionItem(final CompletionItemOptions completionItem) {
    this.completionItem = completionItem;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("resolveProvider", this.resolveProvider);
    b.add("triggerCharacters", this.triggerCharacters);
    b.add("allCommitCharacters", this.allCommitCharacters);
    b.add("completionItem", this.completionItem);
    b.add("workDoneProgress", getWorkDoneProgress());
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
    if (!super.equals(obj))
      return false;
    CompletionOptions other = (CompletionOptions) obj;
    if (this.resolveProvider == null) {
      if (other.resolveProvider != null)
        return false;
    } else if (!this.resolveProvider.equals(other.resolveProvider))
      return false;
    if (this.triggerCharacters == null) {
      if (other.triggerCharacters != null)
        return false;
    } else if (!this.triggerCharacters.equals(other.triggerCharacters))
      return false;
    if (this.allCommitCharacters == null) {
      if (other.allCommitCharacters != null)
        return false;
    } else if (!this.allCommitCharacters.equals(other.allCommitCharacters))
      return false;
    if (this.completionItem == null) {
      if (other.completionItem != null)
        return false;
    } else if (!this.completionItem.equals(other.completionItem))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.resolveProvider== null) ? 0 : this.resolveProvider.hashCode());
    result = prime * result + ((this.triggerCharacters== null) ? 0 : this.triggerCharacters.hashCode());
    result = prime * result + ((this.allCommitCharacters== null) ? 0 : this.allCommitCharacters.hashCode());
    return prime * result + ((this.completionItem== null) ? 0 : this.completionItem.hashCode());
  }
}
