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

import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * An inlay hint label part allows for interactive and composite labels
 * of inlay hints.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlayHintLabelPart {
  /**
   * The value of this label part.
   */
  @NonNull
  private String value;

  /**
   * The tooltip text when you hover over this label part. Depending on
   * the client capability {@link InlayHintCapabilities#resolveSupport} clients might resolve
   * this property late using the resolve request.
   */
  private Either<String, MarkupContent> tooltip;

  /**
   * An optional source code location that represents this
   * label part.
   * <p>
   * The editor will use this location for the hover and for code navigation
   * features: This part will become a clickable link that resolves to the
   * definition of the symbol at the given location (not necessarily the
   * location itself), it shows the hover that shows at the given location,
   * and it shows a context menu with further code navigation commands.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  private Location location;

  /**
   * An optional command for this label part.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  private Command command;

  public InlayHintLabelPart() {
  }

  public InlayHintLabelPart(@NonNull final String value) {
    this.value = Preconditions.<String>checkNotNull(value, "value");
  }

  /**
   * The value of this label part.
   */
  @NonNull
  public String getValue() {
    return this.value;
  }

  /**
   * The value of this label part.
   */
  public void setValue(@NonNull final String value) {
    this.value = Preconditions.checkNotNull(value, "value");
  }

  /**
   * The tooltip text when you hover over this label part. Depending on
   * the client capability {@link InlayHintCapabilities#resolveSupport} clients might resolve
   * this property late using the resolve request.
   */
  public Either<String, MarkupContent> getTooltip() {
    return this.tooltip;
  }

  /**
   * The tooltip text when you hover over this label part. Depending on
   * the client capability {@link InlayHintCapabilities#resolveSupport} clients might resolve
   * this property late using the resolve request.
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
   * An optional source code location that represents this
   * label part.
   * <p>
   * The editor will use this location for the hover and for code navigation
   * features: This part will become a clickable link that resolves to the
   * definition of the symbol at the given location (not necessarily the
   * location itself), it shows the hover that shows at the given location,
   * and it shows a context menu with further code navigation commands.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public Location getLocation() {
    return this.location;
  }

  /**
   * An optional source code location that represents this
   * label part.
   * <p>
   * The editor will use this location for the hover and for code navigation
   * features: This part will become a clickable link that resolves to the
   * definition of the symbol at the given location (not necessarily the
   * location itself), it shows the hover that shows at the given location,
   * and it shows a context menu with further code navigation commands.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public void setLocation(final Location location) {
    this.location = location;
  }

  /**
   * An optional command for this label part.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public Command getCommand() {
    return this.command;
  }

  /**
   * An optional command for this label part.
   * <p>
   * Depending on the client capability {@link InlayHintCapabilities#resolveSupport} clients
   * might resolve this property late using the resolve request.
   */
  public void setCommand(final Command command) {
    this.command = command;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("value", this.value);
    b.add("tooltip", this.tooltip);
    b.add("location", this.location);
    b.add("command", this.command);
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
    InlayHintLabelPart other = (InlayHintLabelPart) obj;
    if (this.value == null) {
      if (other.value != null)
        return false;
    } else if (!this.value.equals(other.value))
      return false;
    if (this.tooltip == null) {
      if (other.tooltip != null)
        return false;
    } else if (!this.tooltip.equals(other.tooltip))
      return false;
    if (this.location == null) {
      if (other.location != null)
        return false;
    } else if (!this.location.equals(other.location))
      return false;
    if (this.command == null) {
      if (other.command != null)
        return false;
    } else if (!this.command.equals(other.command))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.value== null) ? 0 : this.value.hashCode());
    result = prime * result + ((this.tooltip== null) ? 0 : this.tooltip.hashCode());
    result = prime * result + ((this.location== null) ? 0 : this.location.hashCode());
    return prime * result + ((this.command== null) ? 0 : this.command.hashCode());
  }
}
