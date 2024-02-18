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

/**
 * A set of predefined position encoding kinds indicating how
 * positions are encoded, specifically what column offsets mean.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public final class PositionEncodingKind {
  /**
   * Character offsets count UTF-8 code units.
   */
  public static final String UTF8 = "utf-8";

  /**
   * Character offsets count UTF-16 code units.
   * <p>
   * This is the default and must always be supported
   * by servers.
   */
  public static final String UTF16 = "utf-16";

  /**
   * Character offsets count UTF-32 code units.
   * <p>
   * Implementation note: these are the same as Unicode code points,
   * so this kind may also be used for an encoding-agnostic
   * representation of character offsets.
   */
  public static final String UTF32 = "utf-32";

  private PositionEncodingKind() {
  }
}
